package hr.tvz.android.simpleweatherapp.dependency_injection

import hr.tvz.android.simpleweatherapp.storage.SharedPreferencesManager
import org.koin.dsl.module

val storageModule = module {
    single { SharedPreferencesManager(context = get(), gson = get()) }
}