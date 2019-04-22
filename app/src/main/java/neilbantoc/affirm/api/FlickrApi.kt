package neilbantoc.affirm.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApi {

    @GET("rest")
    fun query(
        @Query("api_key") apiKey: String,
        @Query("method") method: Methods,
        @Query("format") format: Formats,
        @Query("text") text: String,
        @Query("page") page: Number,
        @Query("nojsoncallback") noJsonCallback: NoJsonCallback,
        @Query("extras") extras: String
    ) : Single<QueryApiResponse>

    companion object {
        enum class Formats(val toString: String) {
            JSON("json");

            override fun toString(): String {
                return toString
            }
        }

        enum class NoJsonCallback(val toString: String) {
            TRUE("1");

            override fun toString(): String {
                return toString
            }
        }

        enum class Methods(val toString: String) {
            SEARCH("flickr.photos.search");

            override fun toString(): String {
                return toString
            }
        }

        enum class Extras(val toString: String) {
            URL_SMALL("url_s");

            override fun toString(): String {
                return toString
            }
        }
    }
}