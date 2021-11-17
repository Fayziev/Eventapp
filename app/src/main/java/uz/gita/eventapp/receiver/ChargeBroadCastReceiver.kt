package uz.gita.eventapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ChargeBroadCastReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null
    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action.equals(Intent.ACTION_POWER_CONNECTED)) listener?.invoke(true)
        else listener?.invoke(false)

    }

    fun setListener(f: ((Boolean) -> Unit)) {
        listener = f
    }
}