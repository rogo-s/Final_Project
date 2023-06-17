package com.rogo.final_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.DataRegist
import com.rogo.final_project.view.model.data.ResponseDataRegist
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var api: RestfulApi) : ViewModel() {
    private val _usersRegist = MutableLiveData<ResponseDataRegist?>()
    val usersRegist : MutableLiveData<ResponseDataRegist?> = _usersRegist

    fun registDataUser(data : DataRegist){
        api.registerUser(data).enqueue(object : Callback<ResponseDataRegist> {
            override fun onResponse(
                call: Call<ResponseDataRegist>,
                response: Response<ResponseDataRegist>
            ) {
                if(response.isSuccessful){
                    _usersRegist.postValue(response.body())
                }else{
                    _usersRegist.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataRegist>, t: Throwable) {
                _usersRegist.postValue(null)
            }
        })
    }
}