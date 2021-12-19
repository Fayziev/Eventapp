package uz.gita.voiceannouncer23

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import uz.gita.voiceannouncer23.fragment.MyPref

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val pref=MyPref.getPref()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHost.navController.navInflater.inflate(R.navigation.nav_graph)
        val isStart = pref.isStart
        if (isStart) navController.startDestination = R.id.mainFragment
        else navController.startDestination = R.id.introFragment1
        navHost.navController.graph = navController

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}