package com.rogo.final_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.ticket.GetTicketResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val api: RestfulApi) : ViewModel() {
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

}