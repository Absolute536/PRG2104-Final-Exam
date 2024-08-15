package my.game.wl.model

import scalafx.beans.property.{ObjectProperty, StringProperty}

class Score (val playerNameS: String, val pointsI: Int) {
  def this() = this("Player", 0)
  var playerName = new StringProperty(playerNameS)
  var points = ObjectProperty[Int](pointsI)



}