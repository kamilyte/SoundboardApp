package com.example.soundboardapp.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.soundboardapp.model.Soundboard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var soundboardList: MutableList<Soundboard> = _uiState.value.soundboardList
    var searchText: String by mutableStateOf("")
        private set
    var searchResults: List<Soundboard> by mutableStateOf(listOf())
        private set
    var activeSearch: Boolean by mutableStateOf(false)
        private set

    fun onSearchTextChanged(searchText: String) {
        this.searchText = searchText
        this.activeSearch = true

        if (this.searchText.isEmpty()) {
            return
        }
        this.searchResults = soundboardList.filter { soundboard ->
            soundboard.soundboardName.contains(searchText, true)
        }
    }

    fun onClearClick() {
        searchResults = listOf()
        searchText = ""
        activeSearch = false
    }

}