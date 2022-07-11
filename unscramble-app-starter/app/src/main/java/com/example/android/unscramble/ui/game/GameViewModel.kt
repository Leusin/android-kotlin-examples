package com.example.android.unscramble.ui.game

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var score = 0
    private var currentWordCount = 0
    private var _currentScrambledWord = "test"
    val currentScrambledWord: String get() = _currentScrambledWord

    private val TAG = "GameModel"
    init {
        Log.d(TAG, "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GameViewModel destroyed!")
    }
}