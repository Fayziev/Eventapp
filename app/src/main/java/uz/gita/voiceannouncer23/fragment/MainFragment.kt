package uz.gita.voiceannouncer23.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.EventService
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentMainBinding
import uz.gita.voiceannouncer23.utils.checkPermissions

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onResume() {
        super.onResume()
        startService()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().checkPermissions(arrayOf(android.Manifest.permission.READ_PHONE_STATE)) {}
        binding.card1.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_bluetoothFragment)
        }
        binding.card2.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rotateFragment)
        }
        binding.card3.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_airplaneFragment)
        }
        binding.card4.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_chargeFragment)
        }
        binding.card5.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_gpsFragment)
        }
        binding.card6.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_wifiFragment)
        }
        binding.card7.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_hotspotFragment)
        }
        binding.card8.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_screenLockFragment)
        }
        binding.card9.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_networkFragment)
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