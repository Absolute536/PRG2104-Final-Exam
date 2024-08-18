package my.game.wl.model

import scalafx.beans.property.{IntegerProperty, StringProperty}

class Player (val nameS: String) {
  def this() = this("Player")

  val name: StringProperty = new StringProperty(nameS)
  val points: IntegerProperty = IntegerProperty(0)

  def increasePoints(difficulty: Difficulty): Unit = {
    difficulty match {
      case Difficulty.easy => this.points.value += 20
      case Difficulty.medium => this.points.value += 35
      case Difficulty.hard => this.points.value += 50
    }
  }
}