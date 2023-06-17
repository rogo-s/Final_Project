package com.rogo.final_project.view.model.data

import com.google.gson.annotations.SerializedName

data class UserLogin (
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("firstName")
    val firstName : String,
    @SerializedName("lastName")
    val lastName : String,
    @SerializedName("phoneNumber")
    val phoneNumber : String,
    @SerializedName("token")
    val token : String
)