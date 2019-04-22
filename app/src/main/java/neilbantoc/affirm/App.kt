package neilbantoc.affirm

import android.app.Application
import neilbantoc.affirm.api.FlickerApi
import neilbantoc.affirm.api.FlickrApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class App() : Application() {

    private lateinit var flickrApiService: FlickrApiService

    override fun onCreate() {
        super.onCreate()

        val builder = Retrofit.Builder()
            .baseUrl(Config.currentConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            builder.client(client)
        }

        val retrofit = builder.build()

        flickrApiService = FlickrApiService(retrofit.create(FlickerApi::class.java), BuildConfig.FlickrApiKey)
    }

    fun getFlickrApiService(): FlickrApiService {
        return flickrApiService
    }
}