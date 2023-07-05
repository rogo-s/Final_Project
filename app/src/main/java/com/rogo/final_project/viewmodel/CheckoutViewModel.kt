package com.rogo.final_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.checkout.CreateCheckout
import com.rogo.final_project.view.model.data.checkout.ResponseCreateCheckout
import com.rogo.final_project.view.model.data.checkout.ResponseGetCheckout
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(var api: RestfulApi) : ViewModel() {

    //checkoutViewModel
    private val _dataCheckout = MutableLiveData<ResponseCreateCheckout?>()
    val dataCheckout: MutableLiveData<ResponseCreateCheckout?> = _dataCheckout

    //getCheckoutViewModel
    private val _getDataCheckout = MutableLiveData<ResponseGetCheckout?>()
    val getDataCheckout: MutableLiveData<ResponseGetCheckout?> = _getDataCheckout

    fun checkoutUser(accessToken: String, dataCheckout: CreateCheckout) {
        api.checkout("Bearer $accessToken", dataCheckout)
            .enqueue(object : Callback<ResponseCreateCheckout> {
                override fun onResponse(
                    call: Call<ResponseCreateCheckout>,
                    response: Response<ResponseCreateCheckout>
                ) {
                    if (response.isSuccessful) {
                        _dataCheckout.postValue(response.body())
                    } else {
                        _dataCheckout.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseCreateCheckout>, t: Throwable) {
                    _dataCheckout.postValue(null)
                }
            })
    }

    fun getcheckout(accessToken: String) {
        api.getCheckouts("Bearer $accessToken")
            .enqueue(object : Callback<ResponseGetCheckout> {
                override fun onResponse(
                    call: Call<ResponseGetCheckout>,
                    response: Response<ResponseGetCheckout>
                ) {
                    if (response.isSuccessful) {
                        _getDataCheckout.postValue(response.body())
                    } else {
                        _getDataCheckout.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseGetCheckout>, t: Throwable) {
                    _getDataCheckout.postValue(null)
                }
            })
    }
}