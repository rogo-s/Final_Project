package com.rogo.final_project.view.model.data


import com.google.gson.annotations.SerializedName

data class ResponseDataRegist(
    @SerializedName("status")
    val status: String,
    @SerializedName("user")
    val user: User
)