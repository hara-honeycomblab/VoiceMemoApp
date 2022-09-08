package com.example.voicememoapp

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class PlaybackFragment : Fragment() {
    private var mp = MediaPlayer()
    private var path: String = Environment.getExternalStorageDirectory().toString() + "/voise.3gp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playbackingButton: Button = view.findViewById(R.id.playbackingButton)
        val returnButton: Button = view.findViewById(R.id.returnButton)
        playbackingButton.setOnClickListener {
            //音声再生
            startPlay()
        }

        returnButton.setOnClickListener {
            //音声停止
            stopPlay()
            mp.release()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, HomeFragment.newInstance())
                .commit()
        }
    }

    private fun startPlay() {
        stopPlay()
        mp.setDataSource(path)
        mp.prepare()
        mp.start()
    }

    private fun stopPlay() {
        if (mp.isPlaying){
            mp.stop()
            mp.reset()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaybackFragment()
    }
}