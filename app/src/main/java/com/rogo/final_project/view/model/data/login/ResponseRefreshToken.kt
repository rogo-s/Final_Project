package com.rogo.final_project.view.model.data.login


import com.google.gson.annotations.SerializedName

data class ResponseRefreshToken(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("status")
    val status: String
)