package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val TAG = "GameModel"

    private var _score = 0
    private var _currentWordCount = 0
//    private lateinit var _currentScrambledWord: String
    private val _currentScrambledWord = MutableLiveData<String>()

    val score: Int get() = _score
    val currentWordCount: Int get() =_currentWordCount
//    val currentScrambledWord: String get() = _currentScrambledWord
    val currentScrambleWord: LiveData<String> get() = _currentScrambledWord


    // 앱에 사용될 영단어 List
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d(TAG, "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GameViewModel destroyed!")
    }

    /*
    * Updates currentWord and currentScrambledWord with the next word.
    */
    private fun getNextWord() {
        currentWord = allWordsList.random()

        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
//            _currentScrambledWord = String(tempWord)
            _currentScrambledWord.value = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    /*
    * 게임 재시작 할 떄 앱 데이터 재설정
    */
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }

    /*
    * 정답 입력 시 score 가 오름
    */
    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    /*
    * 정답 입력시 true, score 이 오름
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS
    */
    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }


}