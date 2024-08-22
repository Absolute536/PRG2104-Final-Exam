package my.game.wl.model

import my.game.wl.util.{ScoreBoard, Sound, WordSelector}

class Game (
           val player: Player,
           var difficulty: Difficulty
           ) {
  // Default Difficulty is Medium
  def this() = this(new Player, Difficulty.medium)

  val wordSelector: WordSelector = new WordSelector
  var word: String = ""

  val sound: Sound = new Sound
  sound.playBackgroundMusic()

  val scoreBoard: ScoreBoard = new ScoreBoard
  scoreBoard.initialiseEntries()

  def initialiseGame(): Unit = {
    player.points.value = 0
    wordSelector.initialiseWordList(difficulty)
    word = wordSelector.generateWord()
  }

  def nextWord(): Unit = {
    word = wordSelector.generateWord()
  }

  def recordScore(): Unit = {
    scoreBoard.updateEntriesToFile(new Score(player.name.value, player.points.value, difficulty.mode))
  }
}
