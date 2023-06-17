package com.rogo.final_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.DataLogin
import com.rogo.final_project.view.model.data.ResponseDataLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var api: RestfulApi) : ViewModel() {
    private val _usersLogin = MutableLiveData<ResponseDataLogin?>()
    val usersLogin: MutableLiveData<ResponseDataLogin?> = _usersLogin

    fun loginDataUser(data : DataLogin){
        api.loginUser(data).enqueue(object : Callback<ResponseDataLogin> {
            override fun onResponse(
                call: Call<ResponseDataLogin>,
                response: Response<ResponseDataLogin>
            ) {
                if(response.isSuccessful){
                    _usersLogin.postValue(response.body())
                }else{
                    _usersLogin.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataLogin>, t: Throwable) {
                _usersLogin.postValue(null)
            }
        })
    }
}