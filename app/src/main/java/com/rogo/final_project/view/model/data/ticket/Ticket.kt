package com.rogo.final_project.view.model.data.ticket


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("additionalInformation")
    val additionalInformation: String,
    @SerializedName("class")
    val classX: String,
    @SerializedName("createdAt")
    val createdAt: String,
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