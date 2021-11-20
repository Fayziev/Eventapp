package uz.gita.eventapp.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.FragmentWifiBinding

class WifiFragment : Fragment(R.layout.fragment_wifi) {
    private val binding by viewBinding(FragmentWifiBinding::bind)
    private val pref = MyPref.getPref()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.wifi
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.wifi = !pref.wifi
            if (!pref.allAnnouncements && pref.wifi) {
                pref.allAnnouncements = pref.wifi
            }
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}