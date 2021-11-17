package uz.gita.eventapp.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.FragmentIntro2Binding
import uz.gita.eventapp.databinding.FragmentIntroBinding

class Intro2Fragment : Fragment(R.layout.fragment_intro2) {
    private val binding by viewBinding(FragmentIntro2Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_intro2Fragment_to_intro3Fragment)
        }
        binding.prevBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}