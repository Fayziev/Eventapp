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
import uz.gita.eventapp.databinding.FragmentRotateBinding

class CallFragment : Fragment(R.layout.fragment_rotate) {
    private val binding by viewBinding(FragmentRotateBinding::bind)
    private val pref = MyPref.getPref()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startService()
        binding.switchBtn.isChecked = pref.call
        binding.switchBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.call = isChecked
        }
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun startService() {
        val intent = Intent(requireActivity(), EventService::class.java)
        intent.putExtra("data", "data")
        if (Build.VERSION.SDK_INT >= 26) {
            requireActivity().startForegroundService(intent)
        } else requireActivity().startService(intent)
    }
}