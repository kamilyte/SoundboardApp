package com.example.soundboardapp.model

import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.compose.ui.graphics.Color
import java.io.IOException

class AudioTile() {
    private var fileName: String = ""
    private var audioName: String = ""

    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    private var backgroundColor: Color? = null

    //private var permissionToRecordAccepted = false
    //private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    private fun setAudioName(audioName: String) {
        this.audioName = audioName
    }

    private fun setBackgroundColor(backgroundColor: Color) {
        this.backgroundColor = backgroundColor
    }

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