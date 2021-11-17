package uz.gita.eventapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager


class CallReceiver : BroadcastReceiver() {
    private var listenerRotate: ((Int) -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                listenerRotate?.invoke(0)
//                Toast.makeText(context, "Phone Is Ringing", Toast.LENGTH_LONG).show()
            }
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                listenerRotate?.invoke(1)
//                Toast.makeText(context, "Call Recieved", Toast.LENGTH_LONG).show()
            }
            if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                listenerRotate?.invoke(2)
//                Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setListenerRotate(f: (Int) -> Unit) {
        listenerRotate = f
    }

//    private var configOrientation = 0
//    private var listenerRotate:((Int)->Unit)?=null
//    override fun onReceive(context: Context, intent: Intent) {
//        configOrientation = context.resources.configuration.orientation
//        when (configOrientation) {
//            Configuration.ORIENTATION_LANDSCAPE -> {
//            listenerRotate?.invoke(0)
//            } Configuration.ORIENTATION_PORTRAIT -> {
//            listenerRotate?.invoke(1)
//            }
//        }
//    }
//    fun setListenerRotate(f:(Int)->Unit){
//        listenerRotate=f
//    }
}
