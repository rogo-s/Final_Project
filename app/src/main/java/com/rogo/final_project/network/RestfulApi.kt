package com.rogo.final_project.network

import com.rogo.final_project.view.model.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RestfulApi {
    @POST("register")
    fun registerUser(
        @Body data: User
    ) : Call<ResponseDataRegist>

    @POST("login")
    fun loginUser(
        @Body data: DataLogin
    ) : Call<ResponseDataLogin>
}