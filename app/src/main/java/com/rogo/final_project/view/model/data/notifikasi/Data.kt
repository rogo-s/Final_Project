package com.rogo.final_project.view.model.data.notifikasi


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isRead")
    val isRead: Boolean,
    @SerializedName("label")
    val label: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("userId")
    val userId: Int
)