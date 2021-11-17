package uz.gita.eventapp

import android.app.Application
import uz.gita.eventapp.fragment.MyPref

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MyPref.init(instance)
    }
}