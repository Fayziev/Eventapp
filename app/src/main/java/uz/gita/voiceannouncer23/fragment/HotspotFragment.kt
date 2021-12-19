package uz.gita.voiceannouncer23.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentHotspotBinding

class HotspotFragment : Fragment(R.layout.fragment_hotspot) {
    private val binding by viewBinding(FragmentHotspotBinding::bind)
    private val pref = MyPref.getPref()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.hotspot
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.hotspot = !pref.hotspot
            if (!pref.allAnnouncements && pref.hotspot) {
                pref.allAnnouncements = pref.hotspot
            }
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}