package neilbantoc.affirm.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") val id : Long,
    @SerializedName("owner") val owner: String,
    @SerializedName("url_s") val urlSmall: String,
    @SerializedName("title") val title: String,
    @SerializedName("height_s") val heightSmall: Int,
    @SerializedName("width_s") val widthSmall: Int
) {
    fun getAspectRatioString(): String {
        return "H," + widthSmall + ":" + heightSmall
    }
}