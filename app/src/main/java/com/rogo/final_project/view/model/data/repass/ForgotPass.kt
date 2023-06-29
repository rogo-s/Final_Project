package com.rogo.final_project.view.model.data.repass


import com.google.gson.annotations.SerializedName

data class ForgotPass(
    @SerializedName("email")
    val email: String
)