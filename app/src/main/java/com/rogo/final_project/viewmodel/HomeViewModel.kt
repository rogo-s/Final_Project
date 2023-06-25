package com.rogo.final_project.viewmodel

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rogo.final_project.local.datastore.TokenDataStore
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.flight.GetFlightResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: RestfulApi,
    private val dataStore: TokenDataStore
) : ViewModel() {


    private val _flight = MutableLiveData<GetFlightResponse>()
    val flight: MutableLiveData<GetFlightResponse> = _flight

    fun getFlights() {
        api.getFlights().enqueue(object: Callback<GetFlightResponse>{
            override fun onResponse(
                call: Call<GetFlightResponse>,
                response: Response<GetFlightResponse>
            ) {
                if (response.isSuccessful){
                    _flight.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetFlightResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}