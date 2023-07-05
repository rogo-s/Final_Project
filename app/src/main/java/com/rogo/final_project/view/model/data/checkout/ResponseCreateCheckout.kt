package com.rogo.final_project.view.model.data.checkout


import com.google.gson.annotations.SerializedName

data class ResponseCreateCheckout(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)