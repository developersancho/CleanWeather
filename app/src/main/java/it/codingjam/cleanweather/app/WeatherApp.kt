package it.codingjam.cleanweather.app

import inversion.InversionValidate
import it.codingjam.cleanweather.domain.domainComponent
import it.codingjam.cleanweather.domain.domainDependencies
import it.codingjam.cleanweather.utils.ComponentHolderApp

@InversionValidate
class WeatherApp : ComponentHolderApp() {

    private val applicationComponent:ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
                domainDependencies()
        )
    }

    override fun <C : Any> castComponent(): C {
        return applicationComponent as C
    }
}