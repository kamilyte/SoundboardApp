package com.example.soundboardapp.model

import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.compose.ui.graphics.Color
import java.io.IOException

class Audio(id: Int) {
    var fileName: String = ""
    var audioName: String = "Sound #${id}"

    var recorder: MediaRecorder? = null
    var player: MediaPlayer? = null

    var backgroundColor: Color? = null
    var empty: Boolean = true

    //private var permissionToRecordAccepted = false
    //private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)



    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                //TODO
            }
            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                //TODO
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

}