package com.rogo.final_project.network

import com.rogo.final_project.view.model.data.*
import com.rogo.final_project.view.model.data.flight.GetFlightResponse
import com.rogo.final_project.view.model.data.flight.ReqBodyFlight
import com.rogo.final_project.view.model.data.otp.DataOtp
import com.rogo.final_project.view.model.data.otp.DataResendOtp
import com.rogo.final_project.view.model.data.otp.ResponseResendOtp
import com.rogo.final_project.view.model.data.otp.ResponseVerify
import com.rogo.final_project.view.model.data.login.DataLogin
import com.rogo.final_project.view.model.data.login.ResponseDataLogin
import com.rogo.final_project.view.model.data.profile.Data
import com.rogo.final_project.view.model.data.profile.ResponseGetDataProfile
import com.rogo.final_project.view.model.data.profile.ResponseProfileUpdate
import com.rogo.final_project.view.model.data.profile.UpdateProfile
import com.rogo.final_project.view.model.data.register.DataRegist
import com.rogo.final_project.view.model.data.register.ResponseDataRegist
import com.rogo.final_project.view.model.data.ticket.GetTicketResponse
import com.rogo.final_project.view.model.data.ticket.ReqBodyTicket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface RestfulApi {
    @POST("register")
    fun registerUser(
        @Body data: DataRegist
    ): Call<ResponseDataRegist>

    @POST("login")
    fun loginUser(
        @Body data: DataLogin
    ): Call<ResponseDataLogin>

    @POST("verify")
    fun otpUser(
        @Body data: DataOtp
    ): Call<ResponseVerify>

    @POST("resend-otp")
    fun resendOtp(
        @Body data: DataResendOtp
    ): Call<ResponseResendOtp>

//    @DELETE("logout")
//    fun deleteUser(
//        @Body
//    )

    @PUT("profile/update")
    fun updateProfile(
        @Header("Authorization") accessToken: String,
        @Body data: UpdateProfile
    ): Call<ResponseProfileUpdate>

    @GET("profile")
    fun dataProfile(
        @Header("Authorization") accessToken: String
    ): Call<ResponseGetDataProfile>

    @GET("tickets")
    fun getTicket(): Call<GetTicketResponse>

    @GET("flights")
    fun getFlights(
        //    @Header("Authorization") token: String
    ): Call<GetFlightResponse>
}