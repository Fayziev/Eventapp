package uz.gita.eventapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    override fun onReceive(p0: Context?, p1: Intent) {
        if (p1.action.equals(Intent.ACTION_SCREEN_OFF)) {
            listener?.invoke(false)
        } else if (p1.action.equals(Intent.ACTION_SCREEN_ON)) {
            listener?.invoke(true)
        }
    }

    fun setScreenOnListener(f: (Boolean) -> Unit) {
        listener = f
    }
}