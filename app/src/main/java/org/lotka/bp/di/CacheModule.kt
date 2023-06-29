package org.lotka.bp.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.lotka.bp.cache.RecipeDao
import org.lotka.bp.cache.database.AppDatabase
import org.lotka.bp.cache.model.RecipeEntityMapper
import org.lotka.bp.presentation.BaseApplication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDatabase): RecipeDao {
        return db.recipeDao()
    }

    @Singleton
    @Provides
    fun provideCacheRecipeMapper(): RecipeEntityMapper {
        return RecipeEntityMapper()
    }

}







