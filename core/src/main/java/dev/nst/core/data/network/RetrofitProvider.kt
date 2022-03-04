package dev.nst.core.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val URL = "https://run.mocky.io/v3/"
private const val TIMEOUT = 30L

@Singleton
class RetrofitProvider @Inject constructor() {

    private var retrofit: Retrofit
    private val gsonConverterFactory = GsonConverterFactory.create()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    fun <T> createApi(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}