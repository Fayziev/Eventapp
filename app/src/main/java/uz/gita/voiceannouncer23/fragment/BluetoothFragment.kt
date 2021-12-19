package uz.gita.voiceannouncer23.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentBluetoothBinding

class BluetoothFragment : Fragment(R.layout.fragment_bluetooth) {
    private val binding by viewBinding(FragmentBluetoothBinding::bind)
    private val pref = MyPref.getPref()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.bluetooth

        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.bluetooth = !pref.bluetooth
            if (!pref.allAnnouncements && pref.bluetooth) {
                pref.allAnnouncements = pref.bluetooth
            }
        }

        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}