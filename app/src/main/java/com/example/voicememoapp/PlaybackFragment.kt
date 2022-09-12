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
import androidx.activity.OnBackPressedCallback
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class PlaybackFragment : Fragment() {
    private var mp = MediaPlayer()

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
        var voiceStorageDir = File(context?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "voise.3gp")
        if (voiceStorageDir.length() != 0L)
        {
            playbackingButton.setOnClickListener {
                //音声再生
                startPlay(voiceStorageDir.path)
            }
        }

        returnButton.setOnClickListener {
            //音声停止
            parentFragmentManager.popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        stopPlay()
        mp.release()
    }


    private fun startPlay(path: String) {
        stopPlay()
        mp.setDataSource(path)
        mp.prepare()
        mp.start()
    }

    private fun stopPlay() {
        mp.stop()
        mp.reset()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaybackFragment()
    }
}