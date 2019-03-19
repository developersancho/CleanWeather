package it.codingjam.cleanweather.weather

import com.codingjam.cleanweather.entities.Temperature

interface TemperatureRepository {
    suspend fun getTemperature(lat: Double, lon: Double): Temperature
}