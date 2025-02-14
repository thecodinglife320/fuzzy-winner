package com.ad.unscrambleword.ui

import com.ad.unscrambleword.data.MAX_NO_OF_WORDS
import com.ad.unscrambleword.data.SCORE_INCREASE
import com.ad.unscrambleword.data.getUnscrambledWord
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {

   private val viewModel = GameViewModel()

   @Test
   fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {

      var gameUiState = viewModel.uiStateFlow.value
      val correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)

      viewModel.updateUserGuess(correctPlayerWord)
      viewModel.checkUserGuess()

      gameUiState = viewModel.uiStateFlow.value
      // Assert that checkUserGuess() method updates isGuessedWordWrong is updated correctly.
      assertFalse(gameUiState.isGuessedWordWrong)
      // Assert that score is updated correctly.
      assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, gameUiState.score)
   }

   @Test
   fun gameViewModel_IncorrectGuess_ErrorFlagSet() {

      val incorrectWord = "and"

      viewModel.updateUserGuess(incorrectWord)
      viewModel.checkUserGuess()

      val gameUiState = viewModel.uiStateFlow.value

      assertEquals(SCORE_AFTER_FAILED_CORRECT_ANSWER, gameUiState.score)

      assertTrue(gameUiState.isGuessedWordWrong)
   }

   @Test
   fun gameViewModel_Initialization_FirstWordLoaded() {

      val gameUiState = viewModel.uiStateFlow.value
      val unScrambleWord = getUnscrambledWord(gameUiState.currentScrambledWord)

      //assert section
      assertNotEquals(unScrambleWord, gameUiState.currentScrambledWord)

      assertTrue(gameUiState.currentWordCount == 1)

      assertTrue(gameUiState.score == 0)

      assertFalse(gameUiState.isGuessedWordWrong)

      assertFalse(gameUiState.isGameOver)
   }

   @Test
   fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {

      var expectedScore = 0
      var gameUiState = viewModel.uiStateFlow.value
      var correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)

      repeat(MAX_NO_OF_WORDS) {

         viewModel.updateUserGuess(correctPlayerWord)
         viewModel.checkUserGuess()

         expectedScore += SCORE_INCREASE
         gameUiState = viewModel.uiStateFlow.value

         correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)

         assertEquals(expectedScore, gameUiState.score)
      }

      assertEquals(MAX_NO_OF_WORDS, gameUiState.currentWordCount)
      assertTrue(gameUiState.isGameOver)
   }

   @Test
   fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {

      var gameUiState = viewModel.uiStateFlow.value
      val correctPlayerWord = getUnscrambledWord(gameUiState.currentScrambledWord)

      viewModel.updateUserGuess(correctPlayerWord)
      viewModel.checkUserGuess()

      gameUiState = viewModel.uiStateFlow.value
      val lastWordCount = gameUiState.currentWordCount

      viewModel.skipWord()

      gameUiState = viewModel.uiStateFlow.value

      // Assert that score remains unchanged after word is skipped.
      assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, gameUiState.score)

      // Assert that word count is increased by 1 after word is skipped.
      assertEquals(lastWordCount + 1, gameUiState.currentWordCount)
   }

   companion object {
      private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
      private const val SCORE_AFTER_FAILED_CORRECT_ANSWER = 0
   }

}