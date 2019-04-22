package neilbantoc.affirm.api

import com.google.gson.annotations.SerializedName

data class QueryApiResponse(
    @SerializedName("stat") val stat: String,
    @SerializedName("photos") val photos: Photos?,
    @SerializedName("code") val code: Number?,
    @SerializedName("message") val message: String?

)