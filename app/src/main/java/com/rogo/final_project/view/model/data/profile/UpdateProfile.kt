package com.rogo.final_project.view.model.data.profile


import com.google.gson.annotations.SerializedName

data class UpdateProfile(
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String
)