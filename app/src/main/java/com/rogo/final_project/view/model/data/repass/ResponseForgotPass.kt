package com.rogo.final_project.view.model.data.repass


import com.google.gson.annotations.SerializedName

data class ResponseForgotPass(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)