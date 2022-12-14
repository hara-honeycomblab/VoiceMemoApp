package com.example.voicememoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recordButton: Button = view.findViewById(R.id.recordButton)
        val playbackButton: Button = view.findViewById(R.id.playbackButton)
        recordButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("")
                .replace(R.id.main, RecordFragment.newInstance())
                .commit()
        }

        playbackButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("")
                .replace(R.id.main, PlaybackFragment.newInstance())
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}