package my.game.wl.model

import scalafx.beans.property.{ObjectProperty, StringProperty}

class Score (playerNameS: String, pointsI: Int, difficultyS: String) {

  val playerName = new StringProperty(playerNameS)
  val points = ObjectProperty[Int](pointsI)
  val difficulty = new StringProperty(difficultyS)



}