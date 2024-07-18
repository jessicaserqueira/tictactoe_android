package com.jessica.tictactoe.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jessica.tictactoe.domain.model.MatchResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.firstOrNull

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "game_player")

object PreferencesKeys {
    val PLAYER_X_KEY = stringPreferencesKey("playerX")
    val PLAYER_O_KEY = stringPreferencesKey("playerO")
    val MATCH_HISTORY_KEY = stringPreferencesKey("match_history")
}

class DataStoryRepository(context: Context) {
    private val dataStore = context.dataStore
    private val gson = Gson()

    suspend fun savePlayer(key: String, name: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = name
        }
    }

    fun getPlayerName(key: String): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }

    suspend fun saveMatchResults(newMatchResult: MatchResults) {
        val currentResults = getMatchResults().firstOrNull() ?: emptyList()
        val updatedResults = currentResults + newMatchResult
        val json = gson.toJson(updatedResults)
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.MATCH_HISTORY_KEY] = json
        }
    }

    fun getMatchResults(): Flow<List<MatchResults>> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.MATCH_HISTORY_KEY]?.let { json ->
                val type = object : TypeToken<List<MatchResults>>() {}.type
                gson.fromJson<List<MatchResults>>(json, type)
            } ?: emptyList()
        }
    }
}