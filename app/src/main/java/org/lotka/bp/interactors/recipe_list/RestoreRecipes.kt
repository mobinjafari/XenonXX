package org.lotka.bp.interactors.recipe_list

import org.lotka.bp.cache.RecipeDao
import org.lotka.bp.cache.model.RecipeEntityMapper
import org.lotka.bp.domain.data.DataState
import org.lotka.bp.domain.model.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.bp.util.RECIPE_PAGINATION_PAGE_SIZE

/**
 * Restore a list of recipes after process death.
 */
class RestoreRecipes(
  private val recipeDao: RecipeDao,
  private val entityMapper: RecipeEntityMapper,
) {
  fun execute(
    page: Int,
    query: String
  ): Flow<DataState<List<Recipe>>> = flow {
    try {
      emit(DataState.loading())

      // just to show pagination, api is fast
      delay(1000)

      // query the cache
      val cacheResult = if (query.isBlank()){
        recipeDao.restoreAllRecipes(
          pageSize = RECIPE_PAGINATION_PAGE_SIZE,
          page = page
        )
      }
      else{
        recipeDao.restoreRecipes(
          query = query,
          pageSize = RECIPE_PAGINATION_PAGE_SIZE,
          page = page
        )
      }

      // emit List<Recipe> from cache
      val list = entityMapper.fromEntityList(cacheResult)
      emit(DataState.success(list))

    }catch (e: Exception){
      emit(DataState.error<List<Recipe>>(e.message?: "Unknown Error"))
    }
  }
}




