package my.game.wl.model


import my.game.wl.util.{ScoreBoard, Sound, WordSelector}
import scalafx.beans.property.{ObjectProperty, StringProperty}

class Game (
           val player: Player,
           val difficulty: ObjectProperty[Difficulty]
           ) {
  // Default Difficulty is Medium
  def this(_player: Player) = this(_player, ObjectProperty[Difficulty](Difficulty.medium))

  val wordSelector: WordSelector = new WordSelector
  var word: StringProperty = new StringProperty()

  val sound: Sound = new Sound
  sound.playBackgroundMusic()

  val scoreBoard: ScoreBoard = new ScoreBoard
  scoreBoard.initialiseEntries()

  def initialiseGame(): Unit = {
    player.points.value = 0
    wordSelector.initialiseWordList(difficulty.value)
    word.value = wordSelector.generateWord()
  }

  def nextWord(): Unit = {
    word.value = wordSelector.generateWord()
  }

  def recordScore(): Unit = {
    scoreBoard.updateEntriesToFile(new Score(player.name.value, player.points.value, difficulty.value.mode))

  }


}
