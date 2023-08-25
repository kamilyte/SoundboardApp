package com.example.soundboardapp.ui.viewmodel

import com.example.soundboardapp.model.Soundboard

data class HomeUiState (
    val soundboardList: List<Soundboard> = listOf(
        Soundboard(1),
        Soundboard(2),
        Soundboard(3),
        Soundboard(4),
        Soundboard(5),
        Soundboard(6),
        Soundboard(7),
    ),
    val currentSoundboard: Soundboard = Soundboard(0)
)