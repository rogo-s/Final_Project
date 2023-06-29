package com.rogo.final_project.view.model.data.repass


import com.google.gson.annotations.SerializedName

data class ResetPass(
    @SerializedName("email")
    val email: String,
    @SerializedName("confirmation")
    val confirmation: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("token")
    val token: String
)