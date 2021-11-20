package uz.gita.eventapp.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.eventapp.EventService
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.FragmentScreenBinding

class ScreenLockFragment : Fragment(R.layout.fragment_screen) {

    private val binding by viewBinding(FragmentScreenBinding::bind)
    private val pref = MyPref.getPref()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.switchBtn.isChecked = pref.screen
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.screen = !pref.screen
            if (!pref.allAnnouncements && pref.screen) {
                pref.allAnnouncements = pref.screen
            }
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}