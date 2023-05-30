package org.lotka.bp.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.lotka.bp.cache.RecipeDao
import org.lotka.bp.cache.model.RecipeEntity

@Database(entities = [RecipeEntity::class ], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object{
        val DATABASE_NAME: String = "recipe_db"
    }


}