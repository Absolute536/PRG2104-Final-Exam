package my.game.wl.model

import scalafx.beans.property.{ObjectProperty, StringProperty}

class Score (val playerNameS: String, val pointsI: Int, val difficultyS: String) {

  val playerName = new StringProperty(playerNameS)
  val points = ObjectProperty[Int](pointsI)
  val difficulty = new StringProperty(difficultyS)



}