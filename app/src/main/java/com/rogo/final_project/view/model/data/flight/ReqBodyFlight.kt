package com.rogo.final_project.view.model.data.flight


import com.google.gson.annotations.SerializedName

data class ReqBodyFlight(
    @SerializedName("email")
    val email: String,
    @SerializedName("otp")
    val otp: Int
)