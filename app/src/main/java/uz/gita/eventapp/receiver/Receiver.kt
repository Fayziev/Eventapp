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
        airplaneCheck(intent, context)
        bluetoothCheck(intent, context)
        callCheck(intent, context)
        chargeCheck(intent, context)
        networkCheck(intent, context)
        gpsCheck(intent, context)
        hotspotCheck(intent, context)
        screenCheck(intent, context)
        wifiCheck(intent, context)
    }

    private fun airplaneCheck(intent: Intent, context: Context) {
        val airplane = isAirplaneModeOn(context)
        if (airplane) {
            if (pref.airplane && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.airplane_on)
                mediaPlayer.start()
            }
        }
    }

    private fun bluetoothCheck(intent: Intent, context: Context) {
        when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
            BluetoothAdapter.STATE_ON -> {
                if (pref.bluetooth && pref.allAnnouncements) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.onblut)
                    mediaPlayer.start()
                }
            }
            BluetoothAdapter.STATE_OFF -> {
                if (pref.bluetooth && pref.allAnnouncements) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.offblut)
                    mediaPlayer.start()
                }
            }
        }


    }

    private fun callCheck(intent: Intent, context: Context) {
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                if (pref.call && pref.allAnnouncements) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.phoneisringing)
                    mediaPlayer.start()
                }
            }
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                if (pref.call && pref.allAnnouncements) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.callreceived)
                    mediaPlayer.start()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun chargeCheck(intent: Intent, context: Context) {
        if (intent.action.equals(Intent.ACTION_POWER_CONNECTED)) {
            if (pref.charge && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.charger_on)
                mediaPlayer.start()
            }
        } else {
            if (pref.charge && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.charger_off)
                mediaPlayer.start()
            }
        }
    }

    private fun networkCheck(intent: Intent, context: Context) {
        if (intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (pref.network && pref.allAnnouncements) {
                val noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
                )
                if (!noConnectivity) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.mobile_data_on)
                    mediaPlayer.start()
                }
            } else {
                if (pref.network && pref.allAnnouncements) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.mobile_data_off)
                    mediaPlayer.start()
                }
            }
        }


    }

    private fun gpsCheck(intent: Intent, context: Context) {
        val manager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (pref.gps && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.gpson)
                mediaPlayer.start()
            }
        } else {
            if (pref.gps && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.gpsoff)
                mediaPlayer.start()
            }
        }
    }

    private fun hotspotCheck(intent: Intent, context: Context) {
        val state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
        if (WifiManager.WIFI_STATE_ENABLED == state % 10) {
            if (pref.hotspot && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.hotspot_on)
                mediaPlayer.start()
            }
        } else {
            if (pref.hotspot && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.hotspot_off)
                mediaPlayer.start()
            }
        }
    }

    private fun screenCheck(intent: Intent, context: Context) {

        if (intent.action.equals(Intent.ACTION_SCREEN_OFF)) {
            if (pref.screen && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.screenon)
                mediaPlayer.start()
            }
        } else if (intent.action.equals(Intent.ACTION_SCREEN_ON)) {
            if (pref.screen && pref.allAnnouncements) {
                if (mediaPlayer.isPlaying) mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.creenoff)
                mediaPlayer.start()
            }
        }


    }

    private fun wifiCheck(intent: Intent, context: Context) {
        when (intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )) {
            WifiManager.WIFI_STATE_ENABLED -> {
                if (pref.wifi && pref.allAnnouncements) {
                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.wifi_on)
                    mediaPlayer.start()
                }
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                if (pref.wifi && pref.allAnnouncements) {

                    if (mediaPlayer.isPlaying) mediaPlayer.release()
                    mediaPlayer = MediaPlayer.create(context, R.raw.wifi_off)
                    mediaPlayer.start()
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