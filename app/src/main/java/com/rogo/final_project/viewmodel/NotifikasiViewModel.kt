package com.rogo.final_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.checkout.ResponseGetCheckout
import com.rogo.final_project.view.model.data.notifikasi.ResponseGetNotif
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class NotifikasiViewModel @Inject constructor(var api: RestfulApi) : ViewModel() {

    //getNotifikasiViewModel
    private val _getNotifikasi = MutableLiveData<ResponseGetNotif?>()
    val getNotifikasi: MutableLiveData<ResponseGetNotif?> = _getNotifikasi

    fun getNotif(accessToken: String) {
        api.getNotif("Bearer $accessToken")
            .enqueue(object : Callback<ResponseGetNotif> {
                override fun onResponse(
                    call: Call<ResponseGetNotif>,
                    response: Response<ResponseGetNotif>
                ) {
                    if (response.isSuccessful) {
                        _getNotifikasi.postValue(response.body())
                    } else {
                        _getNotifikasi.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetNotif>, t: Throwable) {
                    _getNotifikasi.postValue(null)
                }
            })
    }
}