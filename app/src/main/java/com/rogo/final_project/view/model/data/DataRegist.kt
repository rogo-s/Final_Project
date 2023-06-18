package com.rogo.final_project.view.model.data


import com.google.gson.annotations.SerializedName

data class DataRegist(
    @SerializedName("email")
    val email: String,
//    @SerializedName("firstName")
//    val firstName: String,
//    @SerializedName("lastName")
//    val lastName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String
)