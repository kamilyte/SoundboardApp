package com.example.soundboardapp.model

class Soundboard(id: Int) {
    val id : Int = id
    var soundboardName : String = "Soundboard #${this.id}"
    var audioList : MutableList<Audio> = mutableListOf()

    private val maxAudio : Int = 12

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