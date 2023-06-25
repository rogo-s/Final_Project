package com.rogo.final_project.view.model.data.flight


import com.google.gson.annotations.SerializedName

data class GetFlightResponse(
    @SerializedName("flights")
    val flights: List<Flight>,
    @SerializedName("status")
    val status: String,
    @SerializedName("total")
    val total: Int
)