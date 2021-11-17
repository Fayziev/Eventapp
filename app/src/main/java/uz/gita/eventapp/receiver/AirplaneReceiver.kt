package uz.gita.eventapp.receiver

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings


class AirplaneReceiver : BroadcastReceiver() {
    private var airplaneListener: ((Boolean) -> Unit)? = null

    override fun onReceive(p0: Context, p1: Intent) {
        val airplane = isAirplaneModeOn(p0)
        if (airplane) {
            airplaneListener?.invoke(true)
        } else {
            airplaneListener?.invoke(false)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun isAirplaneModeOn(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Settings.System.getInt(
                context.contentResolver,
                Settings.System.AIRPLANE_MODE_ON, 0
            ) != 0
        } else {
            Settings.Global.getInt(
                context.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0
        }
    }
    fun setAirplaneListener(f: (Boolean) -> Unit) {
        airplaneListener = f
    }
}