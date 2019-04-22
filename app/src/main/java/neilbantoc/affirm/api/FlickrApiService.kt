package neilbantoc.affirm.api

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FlickrApiService(private val flickrApi: FlickerApi, private val apiKey: String) {
    fun search(text: String, page: Number): Single<QueryApiResponse> {
        return flickrApi.query(
            apiKey = apiKey,
            method = FlickerApi.Companion.Methods.SEARCH,
            format = FlickerApi.Companion.Formats.JSON,
            noJsonCallback = FlickerApi.Companion.NoJsonCallback.TRUE,
            text = text,
            page = page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}