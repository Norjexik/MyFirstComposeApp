package com.example.newprojectaugust

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class CounterRepository(context: Context) {

    private val dataStore = context.dataStore

    val counter: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[CounterPreferencesKey] ?: 0
        }

    suspend fun setCounter(value: Int) {
        dataStore.edit { preferences ->
            preferences[CounterPreferencesKey] = value
        }
    }

    companion object {
        private val CounterPreferencesKey = intPreferencesKey("counter_value")
    }
}