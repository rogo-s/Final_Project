package com.rogo.final_project.view.model.data.flight


import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("airline")
    val airline: String,
    @SerializedName("arrivalAirport")
    val arrivalAirport: String,
    @SerializedName("arrivalCity")
    val arrivalCity: String,
    @SerializedName("arrivalDate")
    val arrivalDate: String,
    @SerializedName("arrivalTime")
    val arrivalTime: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("departureAirport")
    val departureAirport: String,
    @SerializedName("departureCity")
    val departureCity: String,
    @SerializedName("departureDate")
    val departureDate: String,
    @SerializedName("departureTime")
    val departureTime: String,
    @SerializedName("flightCode")
    val flightCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)