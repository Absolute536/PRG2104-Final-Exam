package my.game.wl.util

import my.game.wl.model.Difficulty

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.{Failure, Random, Success, Try}

class WordSelector {
  private val wordList: ArrayBuffer[String] = ArrayBuffer[String]()

  // Get the word list from the corresponding word bank text file
  def initialiseWordList(difficulty: Difficulty): Unit = {
    val wordListFile = Try({Source.fromFile(getClass.getResource(s"../../../../${difficulty.mode}.txt").toURI)})
    wordListFile match {
      // Each word is separated by a new line
      case Success(x) => for (word <- x.getLines()) {
        wordList += word
      }
      x.close()
      case Failure(x) => x.printStackTrace()
    }
  }

  def generateWord(): String = {
    wordList(Random.nextInt(wordList.length))
  }

  def clearWordList(): Unit = {
    wordList.clear()
  }

}