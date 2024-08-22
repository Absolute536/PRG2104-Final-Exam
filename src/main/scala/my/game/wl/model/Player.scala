package my.game.wl.model

import scalafx.beans.property.{IntegerProperty, StringProperty}

class Player (nameS: String) {
  def this() = this("Player")

  val name: StringProperty = new StringProperty(nameS)
  val points: IntegerProperty = IntegerProperty(0)

  def increasePoints(difficulty: Difficulty): Unit = {
    this.points.value += difficulty.scorePoint
  }
}
