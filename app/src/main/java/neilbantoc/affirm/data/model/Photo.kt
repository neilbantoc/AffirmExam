package neilbantoc.affirm.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id : Long,
    @SerializedName("owner") val owner: String,
    @SerializedName("url_s") val urlSmall: String,
    @SerializedName("title") val title: String
)