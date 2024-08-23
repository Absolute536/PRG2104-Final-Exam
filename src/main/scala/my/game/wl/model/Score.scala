package my.game.wl.model

import scalafx.beans.property.{ObjectProperty, StringProperty}

class Score (playerNameS: String, pointsI: Int, difficultyS: String) {

  val playerName = new StringProperty(playerNameS) // Player Name
  val points = ObjectProperty[Int](pointsI) // The score of the player in the game
  val difficulty = new StringProperty(difficultyS) // The difficulty selected by the player



}