package hr.tvz.android.simpleweatherapp.network.api

import hr.tvz.android.simpleweatherapp.data.RemoteLocation
import hr.tvz.android.simpleweatherapp.data.RemoteWeatherData
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
        const val API_KEY = "x"
    }

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ) : Response<List<RemoteLocation>>

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ) : Response<RemoteWeatherData>
}