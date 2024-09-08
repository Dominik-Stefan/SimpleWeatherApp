package hr.tvz.android.simpleweatherapp.utils

import android.app.Application
import hr.tvz.android.simpleweatherapp.dependency_injection.networkModule
import hr.tvz.android.simpleweatherapp.dependency_injection.repositoryModule
import hr.tvz.android.simpleweatherapp.dependency_injection.serializerModule
import hr.tvz.android.simpleweatherapp.dependency_injection.storageModule
import hr.tvz.android.simpleweatherapp.dependency_injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    serializerModule,
                    storageModule,
                    networkModule
                )
            )
        }
    }
}