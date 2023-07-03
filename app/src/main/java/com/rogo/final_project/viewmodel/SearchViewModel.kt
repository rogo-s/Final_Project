package com.rogo.final_project.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.search.SearchTiketsResponse
import com.rogo.final_project.view.model.data.ticket.GetTicketResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val api: RestfulApi, private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val _search = MutableLiveData<GetTicketResponse>()
    val search: MutableLiveData<GetTicketResponse> = _search

    fun getTickets() {
        api.getTicket().enqueue(object : Callback<GetTicketResponse> {
            override fun onResponse(
                call: Call<GetTicketResponse>,
                response: Response<GetTicketResponse>
            ) {
                if (response.isSuccessful) {
                    _search.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetTicketResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    private val _searching = MutableLiveData<SearchTiketsResponse>()
    val searching: LiveData<SearchTiketsResponse> = _searching

    fun callGetSearchAirport(departureCity: String) {
        api.getdestinationsfrom(departureCity).enqueue(object : Callback<SearchTiketsResponse> {
            override fun onResponse(
                call: Call<SearchTiketsResponse>,
                response: Response<SearchTiketsResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _searching.postValue(response.body())
                    }
                } else {
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchTiketsResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun saveCityFrom(departureCity:String){
        val editor = sharedPreferences.edit()
        editor.putString("keyFrom",departureCity)
        editor.apply()
    }

    private val _searchingTo = MutableLiveData<SearchTiketsResponse>()
    val searchingTo: LiveData<SearchTiketsResponse> = _searchingTo

    fun callGetSearchTo(arrivalCity: String) {
        api.getdestinationsto(arrivalCity).enqueue(object : Callback<SearchTiketsResponse> {
            override fun onResponse(
                call: Call<SearchTiketsResponse>,
                response: Response<SearchTiketsResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _searchingTo.postValue(response.body())
                    }
                } else {
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchTiketsResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }

    fun saveCityTo(arrivalCity:String){
        val editor = sharedPreferences.edit()
        editor.putString("keyTo",arrivalCity)
        editor.apply()
    }

    val _searchallticket : MutableLiveData<SearchTiketsResponse> = MutableLiveData()
    val livedatasearchallticket : LiveData<SearchTiketsResponse> = _searchallticket

    fun searchallticket(departureCity:String,arrivalCity:String,departureDate:String,arrivedDate:String){
        api.getallticket(departureCity, arrivalCity, departureDate, arrivedDate).enqueue(object : Callback<SearchTiketsResponse>{
            override fun onResponse(
                call: Call<SearchTiketsResponse>,
                response: Response<SearchTiketsResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _searchallticket.postValue(response.body())
                    }
                } else {
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchTiketsResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Cannot send data1")
            }

        })
    }



}