package hr.tvz.android.simpleweatherapp.dependency_injection

import hr.tvz.android.simpleweatherapp.fragments.home.HomeViewModel
import hr.tvz.android.simpleweatherapp.fragments.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(weatherDataRepository = get()) }
    viewModel { LocationViewModel(weatherDataRepository = get()) }
}