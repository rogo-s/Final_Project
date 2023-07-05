package com.rogo.final_project.view.model.data.checkout


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("departureSeat")
    val departureSeat: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("familyName")
    val familyName: String,
    @SerializedName("flights")
    val flights: Flights,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isRoundTrip")
    val isRoundTrip: Boolean,
    @SerializedName("passengersData")
    val passengersData: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("returnSeat")
    val returnSeat: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("ticketId")
    val ticketId: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("userId")
    val userId: Int
)