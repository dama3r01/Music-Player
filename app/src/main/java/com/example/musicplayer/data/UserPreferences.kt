package com.example.musicplayer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferences(private val context: Context) {
    companion object {
        val SHUFFLE_MODE = booleanPreferencesKey("shuffle_mode")
        val REPEAT_MODE = intPreferencesKey("repeat_mode")
    }

    val shuffleMode: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[SHUFFLE_MODE] ?: false
    }

    val repeatMode: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[REPEAT_MODE] ?: 0 // 0 = off, 1 = one, 2 = all
    }

    suspend fun setShuffleMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SHUFFLE_MODE] = enabled
        }
    }

    suspend fun setRepeatMode(mode: Int) {
        context.dataStore.edit { preferences ->
            preferences[REPEAT_MODE] = mode
        }
    }
}
