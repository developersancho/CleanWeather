package it.codingjam.cleanweather.mainapp

import android.app.Activity
import android.widget.Toast
import com.codingjam.cleanweather.entities.City
import com.codingjam.cleanweather.entities.Location
import com.codingjam.cleanweather.entities.Temperature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import it.codingjam.cleanweather.domain.LocationManager
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.main.MainNavigator
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainModuleFake {
    @Provides
    @Singleton
    fun provideLocationManager() = object : LocationManager {
        override suspend fun getLastLocation() = Location(10.1, 20.2)

        override suspend fun getCities(location: Location) = listOf(City("city", "country"))
    }

    @Provides
    @Singleton
    fun provideTemperatureRepository() = object : TemperatureRepository {
        override suspend fun getTemperature(lat: Double, lon: Double) = Temperature(1, 2, 3)

        override suspend fun getForecast(lat: Double, lon: Double) = listOf(
                Temperature(1, 2, 3),
                Temperature(4, 5, 6)
        )
    }

    @Provides
    @Singleton
    fun provideNavigator() = object : MainNavigator {
        override fun openDetail(activity: Activity) {
            Toast.makeText(activity, "Layout clicked!", Toast.LENGTH_LONG).show()
        }
    }
}
