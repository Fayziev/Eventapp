package uz.gita.eventapp.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import uz.gita.eventapp.fragment.MyPref

class GpsReceiver : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    @SuppressLint("ServiceCast")
    override fun onReceive(context: Context, intent: Intent) {
        val manager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            listener?.invoke(true)
//        } else {
//            listener?.invoke(false)
//        }
        val myPref = MyPref.getPref()
        if (myPref.gps) {
            listener?.invoke(manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        }
    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }
}