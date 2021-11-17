package uz.gita.eventapp.fragment

import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.eventapp.EventService
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.FragmentGpsBinding
import uz.gita.eventapp.receiver.GpsReceiver

class GpsFragment : Fragment(R.layout.fragment_gps) {
    private val binding by viewBinding(FragmentGpsBinding::bind)
    private val pref = MyPref.getPref()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startService()
        binding.switchBtn.isChecked = pref.gps
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.gps = isChecked
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun startService() {
        val intent = Intent(requireActivity(), EventService::class.java)
        intent.putExtra("data", "data")
        if (Build.VERSION.SDK_INT >= 26) {
            requireActivity().startForegroundService(intent)
        } else requireActivity().startService(intent)
    }
}