package my.game.wl.model

import scalafx.collections.ObservableBuffer
import my.game.wl.MainApp
import my.game.wl.util.WordSelector
import scalafx.beans.property.ObjectProperty

import java.io.{File, PrintWriter}
import scala.collection.mutable.ListBuffer
import scala.io.BufferedSource
import scala.util.{Failure, Success, Try}

class Game (
           val player: Player,
           var difficulty: ObjectProperty[Difficulty]
           ) {
  val wordSelector: WordSelector = new WordSelector
  difficulty.onChange((_, _, newDiff) => wordSelector.initialiseWordList(newDiff))





  private def recordScore(file: BufferedSource): Unit = {
    var scores = ListBuffer[Score]()
    for (line <- file.getLines().map(l => l.split(","))) {
      scores += new Score(line(0), line(1).toInt)
    }
    scores += new Score(player.name.value, player.points.value)
    scores = scores.sortBy(x => x.points.value).reverse
    scores = scores.take(10)

    val writer = Try(new PrintWriter(new File(getClass.getResource("../../../../TopScore.txt").toURI)))
    writer match {
      case Success(f) => for (s <- scores) {
        f.format("%s, %d", s.playerName.value, new Integer(s.points.value))

      }
      f.close()
      case Failure(f) =>
        f.printStackTrace()
    }

  }


}
