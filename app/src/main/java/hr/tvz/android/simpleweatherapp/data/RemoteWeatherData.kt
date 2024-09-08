package hr.tvz.android.simpleweatherapp.data

import com.google.gson.annotations.SerializedName

data class RemoteWeatherData(
    val current: CurrentWeatherRemote,
    val forecast: ForcastRemote
)

data class CurrentWeatherRemote(
    @SerializedName("temp_c") val temperature: Float,
    val condition: WeatherConditionRemote,
    @SerializedName("wind_kph") val wind: Float,
    val humidity: Int
)

data class ForcastRemote(
    @SerializedName("forecastday") val forecastDay: List<ForcastDayRemote>
)

data class ForcastDayRemote(
    val day: DayRemote,
    val hour: List<ForcastHourRemote>
)

data class DayRemote(
    @SerializedName("daily_chance_of_rain") val chanceOfRain: Int
)

data class ForcastHourRemote(
    val time: String,
    @SerializedName("temp_c") val temperature: Float,
    @SerializedName("feelslike_c") val feelsLikeTemperature: Float,
    val condition: WeatherConditionRemote
)

data class WeatherConditionRemote(
    val icon: String
)
