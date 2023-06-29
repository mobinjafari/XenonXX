package org.lotka.bp.datastore

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.lotka.bp.presentation.BaseApplication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore
@Inject
constructor(val app: BaseApplication) {


    private val Context.datastore by preferencesDataStore("app_preferences")
    private val scope = CoroutineScope(Main)

    init {
        observeDataStore()
    }

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        scope.launch {
            app.datastore.edit { preferences ->
                val current = preferences[DARK_THEME_KEY] ?: false
                preferences[DARK_THEME_KEY] = !current
            }
        }
    }

    private fun observeDataStore() {
        app.datastore.data.onEach { preferences ->
            preferences[DARK_THEME_KEY]?.let { isDarkTheme ->
                isDark.value = isDarkTheme
            }
        }.launchIn(scope)
    }

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme_key")
    }
}