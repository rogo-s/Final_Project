package com.rogo.final_project.view.model.data.checkout


import com.google.gson.annotations.SerializedName

data class CreateCheckout(
    @SerializedName("departureSeat")
    val departureSeat: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("familyName")
    val familyName: String,
    @SerializedName("fullName")
    val fullName: String,
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
    @SerializedName("ticketId")
    val ticketId: String,
    @SerializedName("total")
    val total: Int
)