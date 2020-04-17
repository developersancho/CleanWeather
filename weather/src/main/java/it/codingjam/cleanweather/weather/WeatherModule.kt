package it.codingjam.cleanweather.weather

import inversion.Inversion
import inversion.InversionDef
import inversion.of
import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.TemperatureRepository
import it.codingjam.cleanweather.kotlinutils.ComponentHolder
import it.codingjam.cleanweather.kotlinutils.getOrCreate
import javax.inject.Scope

@Scope
internal annotation class WeatherSingleton

interface WeatherComponent {
    val temperatureRepository: TemperatureRepository
}

@Component(
        modules = [WeatherModule::class],
        dependencies = [WeatherDependencies::class]
)
@WeatherSingleton
interface WeatherComponentImpl: WeatherComponent {
    @Component.Factory
    interface Factory {
        fun create(weatherDependencies: WeatherDependencies): WeatherComponent
    }
}

interface WeatherDependencies {
    val weatherApi: WeatherApi
}

@get:InversionDef
val ComponentHolder.weatherDependencies by Inversion.of(WeatherDependencies::class)

@Module
internal class WeatherModule {

    @WeatherSingleton
    @Provides
    fun provideTemperatureRepository(impl: OpenWeatherTemperatureRepository): TemperatureRepository = impl
}

fun ComponentHolder.weatherComponent(): WeatherComponent = getOrCreate {
    DaggerWeatherComponentImpl
            .factory()
            .create(weatherDependencies())
}