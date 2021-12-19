package uz.gita.voiceannouncer23.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentNetworkBinding

class NetworkFragment : Fragment(R.layout.fragment_network) {
    private val binding by viewBinding(FragmentNetworkBinding::bind)
    private val pref = MyPref.getPref()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.network
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.network = !pref.network
            if (!pref.allAnnouncements && pref.network) {
                pref.allAnnouncements = pref.network
            }
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}