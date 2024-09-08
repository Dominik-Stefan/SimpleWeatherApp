package hr.tvz.android.simpleweatherapp.network.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import hr.tvz.android.simpleweatherapp.data.CurrentLocation
import hr.tvz.android.simpleweatherapp.data.RemoteLocation
import hr.tvz.android.simpleweatherapp.data.RemoteWeatherData
import hr.tvz.android.simpleweatherapp.network.api.WeatherAPI

class WeatherDataRepository(private val weatherAPI: WeatherAPI) {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        fusedLocationProviderClient: FusedLocationProviderClient,
        onSucces: (currentLocation: CurrentLocation) -> Unit,
        onFailure: () -> Unit
    ) {
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location ->
            location ?: onFailure()
            onSucces(
                CurrentLocation(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )
        }.addOnFailureListener{ onFailure() }
    }

    @Suppress("DEPRECATION")
    fun updateAddressText(
        currentLocation: CurrentLocation,
        geocoder: Geocoder
    ): CurrentLocation {
        val latitude = currentLocation.latitude ?: return currentLocation
        val longitude = currentLocation.longitude ?: return currentLocation
        return geocoder.getFromLocation(latitude, longitude, 1)?.let { addresses ->
            val address = addresses[0]
            val addressText = StringBuilder()
            addressText.append(address.locality).append(", ")
            addressText.append(address.adminArea).append(", ")
            addressText.append(address.countryName)
            currentLocation.copy(
                location = addressText.toString()
            )
        } ?: currentLocation
    }

    suspend fun searchLocation(query: String): List<RemoteLocation>? {
        val response = weatherAPI.searchLocation(query = query)
        return if(response.isSuccessful) response.body() else null
    }

    suspend fun getWeatherData(latitude: Double, longitude: Double) : RemoteWeatherData? {
        val response = weatherAPI.getWeatherData(query = "$latitude,$longitude")
        return if (response.isSuccessful) response.body() else null
    }
}