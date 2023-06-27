package com.rogo.final_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.detail.TiketsDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var api : RestfulApi) : ViewModel(){

    val _detail : MutableLiveData<TiketsDetailResponse> = MutableLiveData()

    val livedetailticket : LiveData<TiketsDetailResponse> = _detail

    fun getdetailticket(id:Int) {
        api.detailticket(id).enqueue(object : Callback<TiketsDetailResponse> {
            override fun onResponse(
                call: Call<TiketsDetailResponse>,
                response: Response<TiketsDetailResponse>
            ) {
                if (response.isSuccessful){
                    _detail.value = response.body()
                }
                else{
                    Log.e("DetailViewModel", "Cannot send data")
                }
            }

            override fun onFailure(call: Call<TiketsDetailResponse>, t: Throwable) {
                Log.e("DetailViewModel", "Cannot send data 1")
            }

        })
    }
}