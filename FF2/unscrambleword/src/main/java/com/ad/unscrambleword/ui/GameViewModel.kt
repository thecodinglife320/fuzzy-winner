package com.ad.unscrambleword.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ad.unscrambleword.data.MAX_NO_OF_WORDS
import com.ad.unscrambleword.data.SCORE_INCREASE
import com.ad.unscrambleword.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

   var userGuess by mutableStateOf("")
      private set

   // Game UI state
   private val _uiStateFlow = MutableStateFlow(GameUiState())
   val uiStateFlow: StateFlow<GameUiState>
      get() = _uiStateFlow.asStateFlow()

   private lateinit var pickedWord: String

   private var usedWords: MutableSet<String> = mutableSetOf()

   init {
      resetGame()
   }

   private fun pickRandomWordAndShuffle(): String {
      // Continue picking up a new random word until you get one that hasn't been used before
      pickedWord = allWords.random()
      if (usedWords.contains(pickedWord)) {
         return pickRandomWordAndShuffle()
      } else {
         usedWords.add(pickedWord)
         return shuffleCurrentWord(pickedWord)
      }
   }

   private fun shuffleCurrentWord(word: String): String {
      val tempWord = word.toCharArray()
      tempWord.shuffle()
      while (String(tempWord) == word) {
         tempWord.shuffle()
      }
      return String(tempWord)
   }

   fun resetGame() {
      usedWords.clear()
      _uiStateFlow.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
   }

   fun updateUserGuess(it: String) {
      userGuess = it
      //println("userGuess: $userGuess")
   }

   fun checkUserGuess() {

      if (userGuess.equals(pickedWord, ignoreCase = true))
         updateGameState(_uiStateFlow.value.score.plus(SCORE_INCREASE))
      else
      // User's guess is wrong, show an error
         _uiStateFlow.update { currentState -> currentState.copy(isGuessedWordWrong = true) }

      updateUserGuess("")
   }

   private fun updateGameState(updatedScore: Int) {

      if (usedWords.size == MAX_NO_OF_WORDS) {
         _uiStateFlow.update { currentState ->
            currentState.copy(
               isGuessedWordWrong = false,
               score = updatedScore,
               isGameOver = true
            )
         }
      } else
         _uiStateFlow.update { gameUiState ->
            gameUiState.copy(
               currentWordCount = gameUiState.currentWordCount.inc(),
               isGuessedWordWrong = false,
               currentScrambledWord = pickRandomWordAndShuffle(),
               score = updatedScore
            )
         }

   }

   fun skipWord() {
      updateGameState(_uiStateFlow.value.score)
      // Reset user guess
      updateUserGuess("")
   }
}
