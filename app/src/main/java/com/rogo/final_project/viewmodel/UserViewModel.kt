package com.rogo.final_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var api: RestfulApi) : ViewModel() {

    //loginViewModel
    private val _usersLogin = MutableLiveData<ResponseDataLogin?>()
    val usersLogin: MutableLiveData<ResponseDataLogin?> = _usersLogin

    //RegistViewModel
    private val _usersRegist = MutableLiveData<ResponseDataRegist?>()
    val usersRegist : MutableLiveData<ResponseDataRegist?> = _usersRegist

    //SendOtpViewModel
    private val otp = MutableLiveData<ResponseVerify?>()
    val userOtp: MutableLiveData<ResponseVerify?> = otp

    //resendOtpViewModel
    private val resendOto = MutableLiveData<ResponseResendOtp?>()
    val userResendOto: MutableLiveData<ResponseResendOtp?> = resendOto


    //login
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

    //regist
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


    //verifyOtp
    fun verifyOtpRequest(dataOtp: DataOtp){
        api.otpUser(dataOtp)
            .enqueue(object : Callback<ResponseVerify> {
                override fun onResponse(
                    call: Call<ResponseVerify>,
                    response: Response<ResponseVerify>
                ) {
                    if (response.isSuccessful){
                        userOtp.value =response.body()
                    }
                    else{
                        Log.e("UserViewModel", "Cannot verify data")
                    }
                }
                override fun onFailure(call: Call<ResponseVerify>, t: Throwable) {
                    Log.e("UserViewModel", "Cannot verify data")
                }
            })
    }

    //resendOtp
    fun resendOtpRequest(dataResendOtp: DataResendOtp){
        api.resendOtp(dataResendOtp)
            .enqueue(object : Callback<ResponseResendOtp> {
                override fun onResponse(
                    call: Call<ResponseResendOtp>,
                    response: Response<ResponseResendOtp>
                ) {
                    if (response.isSuccessful){
                        userResendOto.value =response.body()
                    }
                    else{
                        Log.e("UserViewModel", "Cannot send data")
                    }
                }
                override fun onFailure(call: Call<ResponseResendOtp>, t: Throwable) {
                    Log.e("UserViewModel", "Cannot send data")
                }

            })
    }
}