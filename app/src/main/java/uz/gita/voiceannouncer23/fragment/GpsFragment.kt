package uz.gita.voiceannouncer23.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentGpsBinding

class GpsFragment : Fragment(R.layout.fragment_gps) {
    private val binding by viewBinding(FragmentGpsBinding::bind)
    private val pref = MyPref.getPref()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.gps
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.gps = !pref.gps
            if (!pref.allAnnouncements && pref.gps) {
                pref.allAnnouncements = pref.gps
            }
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}