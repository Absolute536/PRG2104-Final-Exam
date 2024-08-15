package my.game.wl.model

import my.game.wl.{MainApp, model}
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer

class Game (
           val player: Player,
           val enemies: ObservableBuffer[Enemy],
           val score: Score,
           var difficulty: Difficulty
           ) {
  /*
  def this(difficulty: Int) = {
    this(new Player, new ObservableBuffer[Enemy](), new Score(player.name, player.point),
      difficulty match {
        case 1 => Difficulty.easy
        case 2 => Difficulty.medium
        case 3 => Difficulty.hard
      })
  }

   */

  def launchGame(): Unit = {

    println("Game started")
  }
}
