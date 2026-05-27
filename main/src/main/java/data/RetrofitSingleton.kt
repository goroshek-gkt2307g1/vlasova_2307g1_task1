package data

import api.ApiServer
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitSingleton {
    // The singleton HTTP client.
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.200.160:8080/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val serverApi: ApiServer = retrofit.create(ApiServer::class.java)

}
