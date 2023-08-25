package com.example.soundboardapp.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import com.example.soundboardapp.R


class Soundboard(id: Int) {
    private val maxAudio : Int = 12
    val id : Int = id
    var soundboardName : String = "Soundboard #${this.id}"
    var audioList : MutableList<Audio> = mutableListOf(
        Audio(1),
        Audio(2),
        Audio(3),
        Audio(4),
        Audio(5),
        Audio(6),
        Audio(7),
        Audio(8),
        Audio(9),
        Audio(10),
        Audio(11),
        Audio(12)
    )

    @SuppressLint("SupportAnnotationUsage")
    @DrawableRes
    var imgSrc: Int = R.drawable.placeholder_foreground

    fun addAudio(audio: Audio) {
        if (audioList.size < maxAudio) {
            this.audioList.add(audio)
        } else {
            //TODO: An exception if the list size is full
        }
    }

    fun deleteAudio(audio: Audio) {
        if (audioList.isNotEmpty()) {
            audioList.remove(audio)
        } else {
            //TODO: An exception if the list size is empty
        }
    }

}