package com.rogo.final_project.view.model.data.detail


import com.google.gson.annotations.SerializedName

data class TiketsDetailResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("ticket")
    val ticket: Ticket
)