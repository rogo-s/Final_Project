package com.rogo.final_project.view.model.data.search


import com.google.gson.annotations.SerializedName

data class SearchTiketsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)