package com.example.voicememoapp


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class RecordFragment : Fragment() {

    private var path: String = Environment.getExternalStorageDirectory().toString() + "/voise.3gp"
    private var mr = MediaRecorder()
    private var mp = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recordingButton: Button = view.findViewById(R.id.recordingButton)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        //音声録音
        recordingButton.setOnTouchListener { v, event ->
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        //録音開始
                        recordingButton.setBackgroundColor(Color.RED)
                        startRecord()
                    }
                    MotionEvent.ACTION_UP -> {
                        //録音停止
                        recordingButton.setBackgroundColor(Color.parseColor("#FF6200EE"))
                        stopRecord()
                    }
                }
                true
            }

        saveButton.setOnClickListener {
            //音声保存
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, HomeFragment.newInstance())
                .commit()
        }
    }

    private fun startRecord() {
        mr.setAudioSource(MediaRecorder.AudioSource.MIC)
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)//THREE_GPP
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)//AMR_NB
        mr.setOutputFile(path)
        mr.prepare()
        mr.start()
    }

    private fun stopRecord() {
        mr.stop()
        mr.reset()
        mr.release()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecordFragment()
    }
}