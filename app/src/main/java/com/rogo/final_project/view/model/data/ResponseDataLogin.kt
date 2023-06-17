package com.rogo.final_project.view.model.data


import com.google.gson.annotations.SerializedName

data class ResponseDataLogin(
    @SerializedName("accesToken")
    val accessToken: String
)