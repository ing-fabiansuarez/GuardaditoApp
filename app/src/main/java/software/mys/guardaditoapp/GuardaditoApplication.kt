package software.mys.guardaditoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import software.mys.guardaditoapp.data.AppContainer
import software.mys.guardaditoapp.data.DefaultAppContainer

@HiltAndroidApp
class GuardaditoApplication : Application()