package pt.dg7.android.example.models

import com.google.gson.annotations.SerializedName

data class Comment(@SerializedName("id") val id: Int?,
                   @SerializedName("postId") val postId: Int,
                   @SerializedName("name") val name: String,
                   @SerializedName("email") val email: String,
                   @SerializedName("body") val body: String)