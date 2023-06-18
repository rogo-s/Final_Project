package com.rogo.final_project.view.model.data


import com.google.gson.annotations.SerializedName

data class ResponseResendOtp(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)