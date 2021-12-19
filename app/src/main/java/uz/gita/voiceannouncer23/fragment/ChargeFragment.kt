package uz.gita.voiceannouncer23.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentChargeBinding

class ChargeFragment : Fragment(R.layout.fragment_charge) {
    private val binding by viewBinding(FragmentChargeBinding::bind)
    private val pref = MyPref.getPref()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.charge
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.charge = !pref.charge
            if (!pref.allAnnouncements && pref.charge) {
                pref.allAnnouncements = pref.charge
            }
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}