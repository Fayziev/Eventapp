package uz.gita.eventapp.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.FragmentIntroBinding

class IntroFragment : Fragment(R.layout.fragment_intro) {
    private val binding by viewBinding(FragmentIntroBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment1_to_intro2Fragment)
        }
    }
}