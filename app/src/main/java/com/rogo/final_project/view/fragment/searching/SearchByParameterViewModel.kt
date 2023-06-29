package com.rogo.final_project.view.fragment.searching

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.ticket.Flight
import com.rogo.final_project.view.model.data.ticket.GetTicketResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchByParameterViewModel @Inject constructor(private val api : RestfulApi) : ViewModel() {
    var liveDataFromTo: MutableLiveData<List<Flight?>?> = MutableLiveData()

    fun getCity(city: String) {
        api.getAllCity(city).enqueue(object : Callback<GetTicketResponse> {
            override fun onResponse(
                call: Call<GetTicketResponse>,
                response: Response<GetTicketResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        val flights = data.tickets // Replace 'getFlights()' with the actual method or property name
//                        liveDataFromTo.postValue(flights)
                    }
                } else {
                    Log.e("Error: ", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetTicketResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}