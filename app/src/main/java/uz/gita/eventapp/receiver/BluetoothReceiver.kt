package uz.gita.eventapp.receiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class BluetoothReceiver : BroadcastReceiver() {
    private var bluetoothListener: ((Int) -> Unit)? = null

    override fun onReceive(p0: Context, p1: Intent) {
        when (p1.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)) {
            BluetoothAdapter.STATE_ON -> bluetoothListener?.invoke(0)
            BluetoothAdapter.STATE_OFF -> bluetoothListener?.invoke(1)
            else -> bluetoothListener?.invoke(-1)
        }
    }

    fun setBluetoothListener(f: (Int) -> Unit) {
        bluetoothListener = f
    }
}