package my.game.wl.model

import my.game.wl.{MainApp, model}
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer

import java.util.{Timer, TimerTask}

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
    val startTime = System.currentTimeMillis()

    val timer = new Timer()
    val tTask = new TimerTask {
      override def run(): Unit = {
        enemies.prepend(Enemy.spawn(startTime))
      }
    }
    timer.schedule(tTask, 1000, 5000) // start spawn after 1 second, and spawn every 5 second


    println("Game started")
    while (player.healthPoint > 0) {


    }
    tTask.cancel()
    println("Game End")

  }

}
