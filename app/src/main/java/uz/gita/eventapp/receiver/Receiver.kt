package uz.gita.eventapp.receiver

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import uz.gita.eventapp.R
import uz.gita.eventapp.fragment.MyPref

class Receiver : BroadcastReceiver() {
    private val pref = MyPref.getPref()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onReceive(context: Context, intent: Intent) {
        mediaPlayer = MediaPlayer()
        if (pref.airplane) {
            val airplane = isAirplaneModeOn(context)
            if (airplane) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.airplane_on)
                mediaPlayer.start()
            }
        }
        if (pref.bluetooth) {
            when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
                BluetoothAdapter.STATE_ON -> {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.onblut)
                    mediaPlayer.start()
                }
                BluetoothAdapter.STATE_OFF -> {
                    if (pref.bluetooth) {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.offblut)
                        mediaPlayer.start()
                    }
                }
            }

            try {
                val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
                if (pref.call) {
                    if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.phoneisringing)
                        mediaPlayer.start()
                    }
                    if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.callreceived)
                        mediaPlayer.start()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (pref.charge) {
                if (intent.action.equals(Intent.ACTION_POWER_CONNECTED)) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.charger_on)
                    mediaPlayer.start()
                } else {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.charger_off)
                    mediaPlayer.start()
                }
            }

            if (pref.network) {
                if (intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    val noConnectivity = intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
                    )
                    if (!noConnectivity) {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.mobile_data_on)
                        mediaPlayer.start()
                    } else {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.mobile_data_off)
                        mediaPlayer.start()
                    }
                }
            }
            if (pref.gps) {
                val manager: LocationManager =
                    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.gpson)
                    mediaPlayer.start()
                } else {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.gpsoff)
                    mediaPlayer.start()
                }
            }
            if (pref.hotspot) {
                val action = intent.action
                if ("android.net.wifi.WIFI_AP_STATE_CHANGED" == action) {
                    val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
                    if (WifiManager.WIFI_STATE_ENABLED == state % 10) {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.hotspot_on)
                        mediaPlayer.start()
                    } else {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.hotspot_off)
                        mediaPlayer.start()
                    }
                }
            }
            if (pref.screen) {

                if (intent.action.equals(Intent.ACTION_SCREEN_OFF)) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.screenon)
                    mediaPlayer.start()
                } else if (intent.action.equals(Intent.ACTION_SCREEN_ON)) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.creenoff)
                    mediaPlayer.start()
                }
            }
            if (pref.wifi) {

                when (intent.getIntExtra(
                    WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN
                )) {
                    WifiManager.WIFI_STATE_ENABLED -> {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.wifi_on)
                        mediaPlayer.start()
                    }
                    WifiManager.WIFI_STATE_DISABLED -> {
                        if (mediaPlayer.isPlaying) mediaPlayer.release()
                        mediaPlayer = MediaPlayer.create(context, R.raw.wifi_off)
                        mediaPlayer.start()
                    }
                }
            }
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
                Settings.Global.AIRPLANE_MODE_ON, 0
            ) != 0
        }
    }
}