package com.rogo.final_project.view.model.data.notifikasi


import com.google.gson.annotations.SerializedName

data class ResponseGetNotif(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)