package uz.gita.eventapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager

class WifiCheckReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )) {
            WifiManager.WIFI_STATE_ENABLED -> {
                listener?.invoke(true)
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                listener?.invoke(false)
            }
        }
    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }

}