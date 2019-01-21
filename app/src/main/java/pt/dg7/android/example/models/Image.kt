package pt.dg7.android.example.models

import com.google.gson.annotations.SerializedName

data class Image(@SerializedName("id") val id: String,
                 @SerializedName("urls") val urls: Urls) {

    data class Urls(@SerializedName("raw") val raw: String,
                    @SerializedName("full") val full: String,
                    @SerializedName("regular") val regular: String,
                    @SerializedName("small") val small: String,
                    @SerializedName("thumb") val thumb: String)
}