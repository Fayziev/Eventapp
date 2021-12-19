package uz.gita.voiceannouncer23.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.voiceannouncer23.R
import uz.gita.voiceannouncer23.databinding.FragmentIntro3Binding

class Intro3Fragment : Fragment(R.layout.fragment_intro3) {
    private val binding by viewBinding(FragmentIntro3Binding::bind)
    private val pref = MyPref.getPref()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.next.setOnClickListener {
            pref.isStart = true
            findNavController().navigate(R.id.action_intro3Fragment_to_mainFragment)
        }
        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}