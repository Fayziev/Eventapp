package uz.gita.eventapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager

class HotspotReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if ("android.net.wifi.WIFI_AP_STATE_CHANGED" == action) {
            val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
            if (WifiManager.WIFI_STATE_ENABLED == state % 10) {
                listener?.invoke(true)
            } else {
                listener?.invoke(false)
            }
        }
    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }
}