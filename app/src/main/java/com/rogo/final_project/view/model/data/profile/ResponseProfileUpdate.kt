package com.rogo.final_project.view.model.data.profile


import com.google.gson.annotations.SerializedName

data class ResponseProfileUpdate(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)