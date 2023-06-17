package com.rogo.final_project.view.model.data


import com.google.gson.annotations.SerializedName

data class DataOtp(
    @SerializedName("email")
    val email: String,
    @SerializedName("otp")
    val otp: Int
)