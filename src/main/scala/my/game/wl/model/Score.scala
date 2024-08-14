package my.game.wl.model

import scalafx.beans.property.{ObjectProperty, StringProperty}

class Score (val playerNameS: String, val pointsI: Int, val timeSurvivedS: String) {
  def this() = this("", 0, "0")
  var playerName = new StringProperty(playerNameS)
  var points = ObjectProperty[Int](pointsI)
  var timeSurvived = new StringProperty(timeSurvivedS)



}