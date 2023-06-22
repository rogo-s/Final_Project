package com.rogo.final_project.view.model.data.profile


import com.google.gson.annotations.SerializedName

data class ResponseGetDataProfile(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)