package com.rogo.final_project.view.model.data.otp


import com.google.gson.annotations.SerializedName

data class ResponseVerify(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)