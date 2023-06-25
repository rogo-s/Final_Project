package com.rogo.final_project.view.model.data.ticket


import com.google.gson.annotations.SerializedName

data class GetTicketResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("tickets")
    val tickets: List<TicketsItem>,
    @SerializedName("total")
    val total: Int
)

data class TicketsItem(

    @field:SerializedName("additionalInformation")
    val additionalInformation: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("flight")
    val flight: Flight? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("flightId")
    val flightId: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("label")
    val label: String? = null,

    @field:SerializedName("class")
    val jsonMemberClass: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class Flight(

    @field:SerializedName("departureTime")
    val departureTime: String? = null,

    @field:SerializedName("flightCode")
    val flightCode: String? = null,

    @field:SerializedName("departureAirport")
    val departureAirport: String? = null,

    @field:SerializedName("departureCity")
    val departureCity: String? = null,

    @field:SerializedName("arrivalCity")
    val arrivalCity: String? = null,

    @field:SerializedName("arrivalDate")
    val arrivalDate: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("arrivalTime")
    val arrivalTime: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("departureDate")
    val departureDate: String? = null,

    @field:SerializedName("airline")
    val airline: String? = null,

    @field:SerializedName("arrivalAirport")
    val arrivalAirport: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
