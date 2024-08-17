package my.game.wl.model

import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalafx.scene.Node

class Player (val nameS: String,  val pointsI: Int) {
  def this() = this("Player", 0)
  def this(name: String) = this(name, 0)

  val name: StringProperty = new StringProperty(nameS)
  val points: IntegerProperty = IntegerProperty(pointsI)

  def increasePoints(point: Int): Unit = {
    this.points.value += 20
  }
}