package org.lotka.bp.presentation.ui.dashboard

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.samples.crane.base.CraneTabBar
import androidx.compose.samples.crane.base.CraneTabs
import androidx.compose.samples.crane.base.ExploreSection
import androidx.compose.samples.crane.data.ExploreModel
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.lotka.bp.R
import org.lotka.bp.presentation.theme.AppTheme
import org.lotka.bp.presentation.theme.BottomSheetShape


typealias OnExploreItemClicked = (ExploreModel) -> Unit

enum class CraneScreen {
  Fly, Sleep, Eat
}


@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun DashboardScreen(
  isDarkTheme: Boolean,
  isNetworkAvailable: Boolean,
  onToggleTheme: () -> Unit,
  onNavigateToRecipeDetailScreen: (String) -> Unit,
  viewModel: DashboardViewModel,
  scaffoldPadding : PaddingValues,
  onDateSelectionClicked: () -> Unit,
  openDrawer: () -> Unit,
  widthSize: WindowWidthSizeClass,
  onExploreItemClicked: OnExploreItemClicked,
) {


  val loading = viewModel.loading.value
  val dialogQueue = viewModel.dialogQueue

  val scaffoldState = rememberScaffoldState()

  AppTheme(
    displayProgressBar = loading,
    scaffoldState = scaffoldState,
    darkTheme = isDarkTheme,
    isNetworkAvailable = isNetworkAvailable,
    dialogQueue = dialogQueue.queue.value,
  ) {
    Scaffold(
      scaffoldState = scaffoldState,
      snackbarHost = {
        scaffoldState.snackbarHostState
      },
    ) {

      val suggestedDestinations by viewModel.suggestedDestinations.observeAsState()
      val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
      val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        CraneScreen.values().size
      }

      val coroutineScope = rememberCoroutineScope()
      val craneScreenValues = CraneScreen.values()



      BackdropScaffold(

        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerShape = BottomSheetShape,
        frontLayerScrimColor = Color.Unspecified,
        backLayerBackgroundColor = if (isDarkTheme) Color.Black else Color.White,
        appBar = {
          HomeTabBar(openDrawer, craneScreenValues[pagerState.currentPage], onTabSelected = {
            coroutineScope.launch {
              pagerState.animateScrollToPage(
                it.ordinal,
                animationSpec = tween(
                  TAB_SWITCH_ANIM_DURATION
                )
              )
            }
          })
        },
        backLayerContent = {
          SearchContent(
            widthSize,
            craneScreenValues[pagerState.currentPage],
            viewModel,
            onPeopleChanged,
            onDateSelectionClicked,
            onExploreItemClicked
          )
        },
        frontLayerContent = {
          HorizontalPager(
            state = pagerState
          ) { page ->
            when (craneScreenValues[page]) {
              CraneScreen.Fly -> {
                suggestedDestinations?.let { destinations ->
                  ExploreSection(
                    darkTheme = isDarkTheme,
                    widthSize = widthSize,
                    title = stringResource(R.string.explore_flights_by_destination),
                    exploreList = destinations,
                    onItemClicked = onExploreItemClicked
                  )
                }
              }
              CraneScreen.Sleep -> {
                ExploreSection(
                  darkTheme = isDarkTheme,
                  widthSize = widthSize,
                  title = stringResource(R.string.explore_properties_by_destination),
                  exploreList = viewModel.hotels,
                  onItemClicked = onExploreItemClicked
                )
              }
              CraneScreen.Eat -> {
                ExploreSection(
                  darkTheme = isDarkTheme,
                  widthSize = widthSize,
                  title = stringResource(R.string.explore_restaurants_by_destination),
                  exploreList = viewModel.restaurants,
                  onItemClicked = onExploreItemClicked
                )
              }

              else -> {}
            }
          }
        }
      )
    }
  }
}



@Composable
private fun HomeTabBar(
  openDrawer: () -> Unit,
  tabSelected: CraneScreen,
  onTabSelected: (CraneScreen) -> Unit,
  modifier: Modifier = Modifier
) {
  CraneTabBar(
    modifier = modifier
      .wrapContentWidth().padding(8.dp),
  ) { tabBarModifier ->
    CraneTabs(
      modifier = tabBarModifier,
      titles = CraneScreen.values().map { it.name },
      tabSelected = tabSelected,
      onTabSelected = { newTab -> onTabSelected(CraneScreen.values()[newTab.ordinal]) }
    )
  }
}

private const val TAB_SWITCH_ANIM_DURATION = 300
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SearchContent(
  widthSize: WindowWidthSizeClass,
  tabSelected: CraneScreen,
  viewModel: DashboardViewModel,
  onPeopleChanged: (Int) -> Unit,
  onDateSelectionClicked: () -> Unit,
  onExploreItemClicked: OnExploreItemClicked
) {
  // Reading datesSelected State from here instead of passing the String from the ViewModel
  // to cause a recomposition when the dates change.
  val selectedDates = viewModel.calendarState.calendarUiState.value.selectedDatesFormatted
  AnimatedContent(
    targetState = tabSelected,
    transitionSpec = {
      fadeIn(
        animationSpec = tween(TAB_SWITCH_ANIM_DURATION, easing = EaseIn)
      ).with(
        fadeOut(
          animationSpec = tween(TAB_SWITCH_ANIM_DURATION, easing = EaseOut)
        )
      ).using(
        SizeTransform(
          sizeAnimationSpec = { _, _ ->
            tween(TAB_SWITCH_ANIM_DURATION, easing = EaseInOut)
          }
        )
      )
    },
  ) { targetState ->
    when (targetState) {
      CraneScreen.Fly -> FlySearchContent(
        widthSize = widthSize,
        datesSelected = selectedDates,
        searchUpdates = FlySearchContentUpdates(
          onPeopleChanged = onPeopleChanged,
          onToDestinationChanged = { viewModel.toDestinationChanged(it) },
          onDateSelectionClicked = onDateSelectionClicked,
          onExploreItemClicked = onExploreItemClicked
        )
      )
      CraneScreen.Sleep -> SleepSearchContent(
        widthSize = widthSize,
        datesSelected = selectedDates,
        sleepUpdates = SleepSearchContentUpdates(
          onPeopleChanged = onPeopleChanged,
          onDateSelectionClicked = onDateSelectionClicked,
          onExploreItemClicked = onExploreItemClicked
        )
      )
      CraneScreen.Eat -> EatSearchContent(
        widthSize = widthSize,
        datesSelected = selectedDates,
        eatUpdates = EatSearchContentUpdates(
          onPeopleChanged = onPeopleChanged,
          onDateSelectionClicked = onDateSelectionClicked,
          onExploreItemClicked = onExploreItemClicked
        )
      )

      else -> {}
    }
  }
}

data class FlySearchContentUpdates(
  val onPeopleChanged: (Int) -> Unit,
  val onToDestinationChanged: (String) -> Unit,
  val onDateSelectionClicked: () -> Unit,
  val onExploreItemClicked: OnExploreItemClicked
)

data class SleepSearchContentUpdates(
  val onPeopleChanged: (Int) -> Unit,
  val onDateSelectionClicked: () -> Unit,
  val onExploreItemClicked: OnExploreItemClicked
)

data class EatSearchContentUpdates(
  val onPeopleChanged: (Int) -> Unit,
  val onDateSelectionClicked: () -> Unit,
  val onExploreItemClicked: OnExploreItemClicked
)


