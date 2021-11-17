package uz.gita.eventapp.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE

class MyPref private constructor(context: Context) {

    companion object {
        private lateinit var instance: MyPref

        fun init(context: Context) {
            instance = MyPref(context)
        }

        fun getPref(): MyPref = instance
    }

    private val pref = context.getSharedPreferences("cache", MODE_PRIVATE)

    var isStart: Boolean
        set(value) {
            pref.edit().putBoolean("screen", value).apply()
        }
        get() = pref.getBoolean("screen", true)
    var airplane: Boolean
        set(value) {
            pref.edit().putBoolean("airplane", value).apply()
        }
        get() = pref.getBoolean("airplane", true)
    var gps: Boolean
        set(value) {
            pref.edit().putBoolean("gps", value).apply()
        }
        get() = pref.getBoolean("gps", true)
    var rotate: Boolean
        set(value) {
            pref.edit().putBoolean("rotate", value).apply()
        }
        get() = pref.getBoolean("rotate", true)
    var network: Boolean
        set(value) {
            pref.edit().putBoolean("network", value).apply()
        }
        get() = pref.getBoolean("network", true)
    var hotspot: Boolean
        set(value) {
            pref.edit().putBoolean("hotspot", value).apply()
        }
        get() = pref.getBoolean("hotspot", true)
    var charge: Boolean
        set(value) {
            pref.edit().putBoolean("charge", value).apply()
        }
        get() = pref.getBoolean("charge", true)
 var bluetooth: Boolean
        set(value) {
            pref.edit().putBoolean("bluetooth", value).apply()
        }
        get() = pref.getBoolean("bluetooth", true)
 var wifi: Boolean
        set(value) {
            pref.edit().putBoolean("wifi", value).apply()
        }
        get() = pref.getBoolean("wifi", true)
 var screen: Boolean
        set(value) {
            pref.edit().putBoolean("screen", value).apply()
        }
        get() = pref.getBoolean("screen", true)

 var call: Boolean
        set(value) {
            pref.edit().putBoolean("call", value).apply()
        }
        get() = pref.getBoolean("call", true)


}