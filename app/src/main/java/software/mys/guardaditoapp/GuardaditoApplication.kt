package software.mys.guardaditoapp

import android.app.Application
import software.mys.guardaditoapp.data.AppContainer
import software.mys.guardaditoapp.data.DefaultAppContainer

class GuardaditoApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}