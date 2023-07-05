package com.rogo.final_project.view.model.data.checkout


import com.google.gson.annotations.SerializedName

data class ResponseGetCheckout(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)