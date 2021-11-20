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
        get() = pref.getBoolean("screen", false)

    var airplane: Boolean
        set(value) {
            pref.edit().putBoolean("airplane", value).apply()
        }
        get() = pref.getBoolean("airplane", false)

    var allAnnouncements: Boolean
        set(value) {
            pref.edit().putBoolean("allAnnouncements", value).apply()
        }
        get() = pref.getBoolean("allAnnouncements", false)

    var gps: Boolean
        set(value) {
            pref.edit().putBoolean("gps", value).apply()
        }
        get() = pref.getBoolean("gps", false)

    var network: Boolean
        set(value) {
            pref.edit().putBoolean("network", value).apply()
        }
        get() = pref.getBoolean("network", false)

    var hotspot: Boolean
        set(value) {
            pref.edit().putBoolean("hotspot", value).apply()
        }
        get() = pref.getBoolean("hotspot", false)

    var charge: Boolean
        set(value) {
            pref.edit().putBoolean("charge", value).apply()
        }
        get() = pref.getBoolean("charge", false)

    var bluetooth: Boolean
        set(value) {
            pref.edit().putBoolean("bluetooth", value).apply()
        }
        get() = pref.getBoolean("bluetooth", false)

    var wifi: Boolean
        set(value) {
            pref.edit().putBoolean("wifi", value).apply()
        }
        get() = pref.getBoolean("wifi", false)

    var screen: Boolean
        set(value) {
            pref.edit().putBoolean("screen", value).apply()
        }
        get() = pref.getBoolean("screen", false)

    var call: Boolean
        set(value) {
            pref.edit().putBoolean("call", value).apply()
        }
        get() = pref.getBoolean("call", false)


}