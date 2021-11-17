package uz.gita.eventapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import uz.gita.eventapp.receiver.Receiver

class EventService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null
    private val CHANNEL_ID = "MY_MUSIC"
    private val receiver = Receiver()

    private val notification by lazy {
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setSilent(true)
            .setContentTitle("Voice Announcer")
            .setContentText("We are here to announce what event is happening")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Voice Announcer",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(null, null)
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        startForeground(7, notification)
        val intentFilter3 = IntentFilter()
        intentFilter3.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter3.addAction(Intent.ACTION_POWER_DISCONNECTED)
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)

        this.registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        this.registerReceiver(receiver, IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
        this.registerReceiver(receiver, IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED))
        this.registerReceiver(receiver, IntentFilter(intentFilter3))
        this.registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        this.registerReceiver(receiver, IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED"))
        this.registerReceiver(receiver, IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION))
        this.registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        this.registerReceiver(receiver, IntentFilter(intentFilter))
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
        unregisterReceiver(receiver)
    }

}