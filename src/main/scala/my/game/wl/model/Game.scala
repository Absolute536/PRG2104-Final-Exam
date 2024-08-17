package my.game.wl.model

import scalafx.collections.ObservableBuffer
import my.game.wl.MainApp
import my.game.wl.util.{Sound, WordSelector}
import scalafx.beans.property.{ObjectProperty, StringProperty}
import java.io.{File, PrintWriter}
import java.util.Formatter
import scala.collection.mutable.ListBuffer
import scala.io.BufferedSource
import scala.util.{Failure, Success, Try}

class Game (
           val player: Player,
           val difficulty: ObjectProperty[Difficulty]
           ) {
  // Default Difficulty is Medium
  def this(_player: Player) = this(_player, ObjectProperty[Difficulty](Difficulty.medium))

  val wordSelector: WordSelector = new WordSelector
  var word: StringProperty = new StringProperty()

  val sound: Sound

  def initialiseGame(): Unit = {
    player.points.value = 0
    wordSelector.initialiseWordList(difficulty.value)
    word.value = wordSelector.generateWord()
  }

  var currentCharIndex: Int = 0
  def checkCorrectChar(character: String): Boolean = {
    if (word.value(currentCharIndex).toString == character) {
      true
    }
    else {
      false
    }
  }

  def nextWord(): Unit = {
    word.value = wordSelector.generateWord()
  }

  def recordScore(): Unit = {
    val writer = new Formatter(new File(getClass.getResource("../../../../TopScore.txt").toURI))
    writer.format("%s,%d\n", player.name.value, new Integer(player.points.value))
    writer.flush()
    writer.close()


  }


}
