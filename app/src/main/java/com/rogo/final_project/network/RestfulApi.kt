package com.rogo.final_project.network

import com.rogo.final_project.view.model.data.*
import com.rogo.final_project.view.model.data.checkout.CreateCheckout
import com.rogo.final_project.view.model.data.checkout.ResponseCreateCheckout
import com.rogo.final_project.view.model.data.checkout.ResponseGetCheckout
import com.rogo.final_project.view.model.data.detail.TiketsDetailResponse
import com.rogo.final_project.view.model.data.flight.GetFlightResponse
import com.rogo.final_project.view.model.data.otp.DataOtp
import com.rogo.final_project.view.model.data.otp.DataResendOtp
import com.rogo.final_project.view.model.data.otp.ResponseResendOtp
import com.rogo.final_project.view.model.data.otp.ResponseVerify
import com.rogo.final_project.view.model.data.login.DataLogin
import com.rogo.final_project.view.model.data.login.ResponseDataLogin
import com.rogo.final_project.view.model.data.login.ResponseRefreshToken
import com.rogo.final_project.view.model.data.notifikasi.ResponseGetNotif
import com.rogo.final_project.view.model.data.profile.ResponseGetDataProfile
import com.rogo.final_project.view.model.data.profile.ResponseProfileUpdate
import com.rogo.final_project.view.model.data.profile.UpdateProfile
import com.rogo.final_project.view.model.data.register.DataRegist
import com.rogo.final_project.view.model.data.register.ResponseDataRegist
import com.rogo.final_project.view.model.data.repass.ForgotPass
import com.rogo.final_project.view.model.data.repass.ResetPass
import com.rogo.final_project.view.model.data.repass.ResponseForgotPass
import com.rogo.final_project.view.model.data.search.SearchTiketsResponse
import com.rogo.final_project.view.model.data.ticket.GetTicketResponse
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {
    @POST("register")
    fun registerUser(
        @Body data: DataRegist
    ): Call<ResponseDataRegist>

    @POST("login")
    fun loginUser(
        @Body data: DataLogin
    ): Call<ResponseDataLogin>

    @GET("token")
    fun refreshToken(
    ): Call<ResponseRefreshToken>

    @POST("verify")
    fun otpUser(
        @Body data: DataOtp
    ): Call<ResponseVerify>

    @POST("resend-otp")
    fun resendOtp(
        @Body data: DataResendOtp
    ): Call<ResponseResendOtp>

    @PUT("profile/update")
    fun updateProfile(
        @Header("Authorization") accessToken: String,
        @Body data: UpdateProfile
    ): Call<ResponseProfileUpdate>

    @GET("profile")
    fun dataProfile(
        @Header("Authorization") accessToken: String
    ): Call<ResponseGetDataProfile>

    @PUT("forgot-password")
    fun forgotPass(
        @Body data: ForgotPass
    ): Call<ResponseForgotPass>

    @PUT("reset-password")
    fun resetPass(
        @Body data: ResetPass
    ): Call<List<DataRegist>>

    @GET("tickets")
    fun getTicket(): Call<GetTicketResponse>

    @GET("flights")
    fun getFlights(
        //    @Header("Authorization") token: String
    ): Call<GetFlightResponse>

    @GET("tickets/{id}")
    fun detailticket(
        @Path("id") id:Int
    ):Call<TiketsDetailResponse>

    @GET("search")
    fun getdestinationsfrom(
        @Query("departureCity") departureCity:String,
    ): Call<SearchTiketsResponse>

    @GET("search")
    fun getdestinationsto(
        @Query("arrivalCity") arrivalCity   :String,
    ): Call<SearchTiketsResponse>

    @GET("search")
    fun getallticket(
        @Query("departureCity") departureCity:String,
        @Query("arrivalCity") arrivalCity:String,
        @Query("departureDate") departureDate:String,
        @Query("arrivalDate") arrivedDate:String
    ) : Call<SearchTiketsResponse>

    @GET("airports")
    fun getAllCity(
        @Query ("departureCity") City:String
    ) : Call<GetTicketResponse>

    @POST("checkout")
    fun checkout(
        @Header("Authorization") accessToken: String,
        @Body dataCheckout : CreateCheckout
    ) : Call<ResponseCreateCheckout>

    @GET("checkouts")
    fun getCheckouts(
        @Header("Authorization") accesToken: String
    ) : Call<ResponseGetCheckout>

    @GET("notification")
    fun getNotif(
        @Header("Authorization") accesToken: String
    ) : Call<ResponseGetNotif>
}