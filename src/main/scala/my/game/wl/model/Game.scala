package my.game.wl.model

import scalafx.collections.ObservableBuffer
import my.game.wl.MainApp

class Game (
           val player: Player,
           val enemies: ObservableBuffer[Enemy],
           var difficulty: Difficulty,
           val score: Score
           ) {

  def generateEnemy(): Unit = {
    MainApp.game.enemies += Enemy.slime
  }
}