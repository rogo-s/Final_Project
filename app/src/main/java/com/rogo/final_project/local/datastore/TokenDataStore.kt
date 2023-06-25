package com.rogo.final_project.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Provides
import kotlinx.coroutines.flow.map
import javax.inject.Singleton



class TokenDataStore (private val dataStore: DataStore<Preferences>) {
    val getToken = dataStore.data.map {
        it[stringPreferencesKey("TOKEN")] ?: ""
    }

    suspend fun setToken(token: String) = dataStore.edit {
        it[stringPreferencesKey("TOKEN")] = "Bearer $token"
    }
}