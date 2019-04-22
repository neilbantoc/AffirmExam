package neilbantoc.affirm.api

import com.google.gson.annotations.SerializedName
import neilbantoc.affirm.data.model.Photo

data class Photos (
    @SerializedName("page") val page : Number,
    @SerializedName("pages") val pages : Number,
    @SerializedName("perpage") val perPage : Number,
    @SerializedName("total") val total : Number,
    @SerializedName("photo") val photos : List<Photo>
)