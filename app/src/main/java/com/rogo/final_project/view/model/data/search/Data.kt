package com.rogo.final_project.view.model.data.search


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("additionalInformation")
    val additionalInformation: String,
    @SerializedName("availableSeat")
    val availableSeat: String,
    @SerializedName("classSeat")
    val classSeat: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight")
    val flight: Flight,
    @SerializedName("flightId")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)