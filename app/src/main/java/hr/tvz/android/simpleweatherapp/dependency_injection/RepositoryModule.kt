package hr.tvz.android.simpleweatherapp.dependency_injection

import hr.tvz.android.simpleweatherapp.network.repository.WeatherDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherDataRepository(weatherAPI = get()) }
}