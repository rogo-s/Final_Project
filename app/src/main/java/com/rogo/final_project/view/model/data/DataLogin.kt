package com.rogo.final_project.view.model.data

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("password")
    val password: String
)
