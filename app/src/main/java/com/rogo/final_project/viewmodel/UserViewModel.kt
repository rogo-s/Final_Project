package com.rogo.final_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.otp.DataOtp
import com.rogo.final_project.view.model.data.otp.DataResendOtp
import com.rogo.final_project.view.model.data.otp.ResponseResendOtp
import com.rogo.final_project.view.model.data.otp.ResponseVerify
import com.rogo.final_project.view.model.data.login.DataLogin
import com.rogo.final_project.view.model.data.login.ResponseDataLogin
import com.rogo.final_project.view.model.data.profile.ResponseGetDataProfile
import com.rogo.final_project.view.model.data.profile.UpdateProfile
import com.rogo.final_project.view.model.data.register.DataRegist
import com.rogo.final_project.view.model.data.register.ResponseDataRegist
import com.rogo.final_project.view.model.data.repass.ForgotPass
import com.rogo.final_project.view.model.data.repass.ResetPass
import com.rogo.final_project.view.model.data.repass.ResponseForgotPass
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor( var api: RestfulApi) : ViewModel() {

    //loginViewModel
    private val _usersLogin = MutableLiveData<ResponseDataLogin?>()
    val usersLogin: MutableLiveData<ResponseDataLogin?> = _usersLogin

    //RegistViewModel
    private val _usersRegist = MutableLiveData<ResponseDataRegist?>()
    val usersRegist: MutableLiveData<ResponseDataRegist?> = _usersRegist

    //SendOtpViewModel
    private val otp = MutableLiveData<ResponseVerify?>()
    val userOtp: MutableLiveData<ResponseVerify?> = otp

    //resendOtpViewModel
    private val resendOto = MutableLiveData<ResponseResendOtp?>()
    val userResendOto: MutableLiveData<ResponseResendOtp?> = resendOto

    //deleteViewModel
//    private val _usersLogout = MutableLiveData<ResponseLogout>()
//    val usersLogout : MutableLiveData<ResponseLogout> = _usersLogout

    //updateViewModel
    private val _profileUpdate = MutableLiveData<ResponseDataRegist?>()
    val profileUpdate: MutableLiveData<ResponseDataRegist?> = _profileUpdate

    //getProfileViewModel
    private val _usersGetProfile = MutableLiveData<ResponseGetDataProfile?>()
    val usersGetProfile: MutableLiveData<ResponseGetDataProfile?> = _usersGetProfile

    //sendForgotPassViewModel
    private val _forgotPass = MutableLiveData<ResponseForgotPass?>()
    val forgotPass: MutableLiveData<ResponseForgotPass?> = _forgotPass

    //resetPassViewModel
    private val _resetPass = MutableLiveData<List<DataRegist>?>()
    val resetPass: MutableLiveData<List<DataRegist>?> = _resetPass

    //login
    fun loginDataUser(data: DataLogin) {
        api.loginUser(data).enqueue(object : Callback<ResponseDataLogin> {
            override fun onResponse(
                call: Call<ResponseDataLogin>,
                response: Response<ResponseDataLogin>
            ) {
                if (response.isSuccessful) {
                    _usersLogin.postValue(response.body())
//                    viewModelScope.launch {
//                        dataStore.setToken(response.body()?.accessToken!!)
//                    }
                } else {
                    _usersLogin.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataLogin>, t: Throwable) {
                _usersLogin.postValue(null)
            }
        })
    }

    //regist
    fun registDataUser(data: DataRegist) {
        api.registerUser(data).enqueue(object : Callback<ResponseDataRegist> {
            override fun onResponse(
                call: Call<ResponseDataRegist>,
                response: Response<ResponseDataRegist>
            ) {
                if (response.isSuccessful) {
                    _usersRegist.postValue(response.body())
                } else {
                    _usersRegist.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataRegist>, t: Throwable) {
                _usersRegist.postValue(null)
            }
        })
    }


    //verifyOtp
    fun verifyOtpRequest(dataOtp: DataOtp) {
        api.otpUser(dataOtp)
            .enqueue(object : Callback<ResponseVerify> {
                override fun onResponse(
                    call: Call<ResponseVerify>,
                    response: Response<ResponseVerify>
                ) {
                    if (response.isSuccessful) {
                        userOtp.value = response.body()
                    } else {
                        Log.e("UserViewModel", "Cannot verify data")
                    }
                }

                override fun onFailure(call: Call<ResponseVerify>, t: Throwable) {
                    Log.e("UserViewModel", "Cannot verify data")
                }
            })
    }

    //resendOtp
    fun resendOtpRequest(dataResendOtp: DataResendOtp) {
        api.resendOtp(dataResendOtp)
            .enqueue(object : Callback<ResponseResendOtp> {
                override fun onResponse(
                    call: Call<ResponseResendOtp>,
                    response: Response<ResponseResendOtp>
                ) {
                    if (response.isSuccessful) {
                        userResendOto.value = response.body()
                    } else {
                        Log.e("UserViewModel", "Cannot send data")
                    }
                }

                override fun onFailure(call: Call<ResponseResendOtp>, t: Throwable) {
                    Log.e("UserViewModel", "Cannot send data")
                }

            })
    }

//    fun logoutDataUser(data : DataRegist){
//        api.deleteUser(data).enqueue(object : Callback<ResponseLogout> {
//            override fun onResponse(
//                call: Call<ResponseLogout>,
//                response: Response<ResponseLogout>
//            ) {
//                if(response.isSuccessful){
//                    _usersLogout.postValue(response.body())
//                }else{
//                    _usersLogout.postValue(null)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseLogout>, t: Throwable) {
//                _usersLogout.postValue(null)
//            }
//        })
//    }

    fun updateDataProfile(accessToken: String, data: UpdateProfile) {
        api.updateProfile("Bearer $accessToken", data)
            .enqueue(object : Callback<ResponseDataRegist> {
                override fun onResponse(
                    call: Call<ResponseDataRegist>,
                    response: Response<ResponseDataRegist>
                ) {
                    if (response.isSuccessful) {
                        _profileUpdate.postValue(response.body())
                    } else {
                        _profileUpdate.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataRegist>, t: Throwable) {
                    _profileUpdate.postValue(null)
                }
            })
    }

    fun getDataProfile(accessToken: String) {
        api.dataProfile("Bearer $accessToken").enqueue(object : Callback<ResponseGetDataProfile> {
            override fun onResponse(
                call: Call<ResponseGetDataProfile>,
                response: Response<ResponseGetDataProfile>
            ) {
                if (response.isSuccessful) {
                    _usersGetProfile.postValue(response.body())
                } else {
                    _usersGetProfile.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseGetDataProfile>, t: Throwable) {
                _usersGetProfile.postValue(null)
            }
        })
    }

    fun forgotPassword(data: ForgotPass) {
        api.forgotPass(data).enqueue(object : Callback<ResponseForgotPass> {
            override fun onResponse(
                call: Call<ResponseForgotPass>,
                response: Response<ResponseForgotPass>
            ) {
                if (response.isSuccessful) {
                    _forgotPass.postValue(response.body())
                } else {
                    _forgotPass.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseForgotPass>, t: Throwable) {
                _forgotPass.postValue(null)
            }
        })
    }

    fun resetPassword(data: ResetPass) {
        api.resetPass(data).enqueue(object : Callback<List<DataRegist>> {
            override fun onResponse(
                call: Call<List<DataRegist>>,
                response: Response<List<DataRegist>>
            ) {
                if (response.isSuccessful) {
                    _resetPass.postValue(response.body())
                } else {
                    _resetPass.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<DataRegist>>, t: Throwable) {
                _resetPass.postValue(null)
            }
        })
    }
}