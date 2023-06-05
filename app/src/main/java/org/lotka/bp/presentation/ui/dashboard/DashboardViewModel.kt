package org.lotka.bp.presentation.ui.dashboard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.samples.crane.data.DestinationsRepository
import androidx.compose.samples.crane.data.ExploreModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.lotka.bp.domain.model.Recipe
import org.lotka.bp.interactors.recipe_list.RestoreRecipes
import org.lotka.bp.interactors.recipe_list.SearchRecipes
import org.lotka.bp.presentation.ui.util.DialogQueue
import org.lotka.bp.presentation.util.ConnectivityManager
import org.lotka.bp.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.lotka.bp.di.DefaultDispatcher
import org.lotka.bp.presentation.ui.calendar.model.CalendarState
import org.lotka.bp.presentation.ui.crane.MAX_PEOPLE
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

const val PAGE_SIZE = 30

const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"

@HiltViewModel
class DashboardViewModel
@Inject
constructor(
    private val searchRecipes: SearchRecipes,
    private val restoreRecipes: RestoreRecipes,
    private val connectivityManager: ConnectivityManager,
    private @Named("auth_token") val token: String,
    private val savedStateHandle: SavedStateHandle,
    private val destinationsRepository: DestinationsRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {


    val hotels: List<ExploreModel> = destinationsRepository.hotels
    val restaurants: List<ExploreModel> = destinationsRepository.restaurants

    @RequiresApi(Build.VERSION_CODES.O)
    val calendarState = CalendarState()

    private val _suggestedDestinations = MutableLiveData<List<ExploreModel>>()
    val suggestedDestinations: LiveData<List<ExploreModel>>
        get() = _suggestedDestinations

    init {
        _suggestedDestinations.value = destinationsRepository.destinations
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onDaySelected(daySelected: LocalDate) {
        viewModelScope.launch {
            calendarState.setSelectedDay(daySelected)
        }
    }

    fun updatePeople(people: Int) {
        viewModelScope.launch {
            if (people > MAX_PEOPLE) {
                _suggestedDestinations.value = emptyList()
            } else {
                val newDestinations = withContext(defaultDispatcher) {
                    destinationsRepository.destinations
                        .shuffled(Random(people * (1..100).shuffled().first()))
                }
                _suggestedDestinations.value = newDestinations
            }
        }
    }

    fun toDestinationChanged(newDestination: String) {
        viewModelScope.launch {
            val newDestinations = withContext(defaultDispatcher) {
                destinationsRepository.destinations
                    .filter { it.city.nameToDisplay.contains(newDestination) }
            }
            _suggestedDestinations.value = newDestinations
        }
    }






    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectedCategory: MutableState<DashboardCategory?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    // Pagination starts at '1' (-1 = exhausted)
    val page = mutableStateOf(1)

    var recipeListScrollPosition = 0

    val dialogQueue = DialogQueue()

    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }
        savedStateHandle.get<DashboardCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { c ->
            setSelectedCategory(c)
        }

        if(recipeListScrollPosition != 0){
            onTriggerEvent(DashboardEvent.RestoreStateEvent)
        }
        else{
            onTriggerEvent(DashboardEvent.NewSearchEvent)
        }

    }

    fun onTriggerEvent(event: DashboardEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is DashboardEvent.NewSearchEvent -> {
                        newSearch()
                    }
                    is DashboardEvent.NextPageEvent -> {
                        nextPage()
                    }
                    is DashboardEvent.RestoreStateEvent -> {
                        restoreState()
                    }
                }
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
            finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }

    private fun restoreState() {
        restoreRecipes.execute(page = page.value, query = query.value).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                recipes.value = list
            }

            dataState.error?.let { error ->
                dialogQueue.appendErrorMessage("An Error Occurred", error)
            }
        }.launchIn(viewModelScope)
    }

    private fun newSearch() {
        Log.d(TAG, "newSearch: query: ${query.value}, page: ${page.value}")
        // New search. Reset the state
        resetSearchState()

        searchRecipes.execute(token = token, page = page.value, query = query.value,).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                recipes.value = list
            }

            dataState.error?.let { error ->
                Log.e(TAG, "newSearch: ${error}")
                dialogQueue.appendErrorMessage("An Error Occurred", error)
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            incrementPage()
            Log.d(TAG, "nextPage: triggered: ${page.value}")

            if (page.value > 1) {
                searchRecipes.execute(token = token, page = page.value, query = query.value).onEach { dataState ->
                    loading.value = dataState.loading

                    dataState.data?.let { list ->
                        appendRecipes(list)
                    }

                    dataState.error?.let { error ->
                        Log.e(TAG, "nextPage: ${error}")
                        dialogQueue.appendErrorMessage("An Error Occurred", error)
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    /**
     * Append new recipes to the current list of recipes
     */
    private fun appendRecipes(recipes: List<Recipe>){
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun incrementPage(){
        setPage(page.value + 1)
    }

    fun onChangeRecipeScrollPosition(position: Int){
        setListScrollPosition(position = position)
    }

    /**
     * Called when a new search is executed.
     */
    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if (selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        setSelectedCategory(null)
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getDashboardCategory(category)
        setSelectedCategory(newCategory)
        onQueryChanged(category)
    }

    private fun setListScrollPosition(position: Int){
        recipeListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int){
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setSelectedCategory(category: DashboardCategory?){
        selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun setQuery(query: String){
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }
}
















