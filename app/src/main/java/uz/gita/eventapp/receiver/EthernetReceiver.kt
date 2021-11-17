package uz.gita.eventapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class EthernetReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            val noConnectivity = intent.getBooleanExtra(
                ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            )
            if (!noConnectivity) {
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