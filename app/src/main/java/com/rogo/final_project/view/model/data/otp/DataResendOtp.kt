package com.rogo.final_project.view.model.data.otp


import com.google.gson.annotations.SerializedName

data class DataResendOtp(
    @SerializedName("email")
    val email: String
)