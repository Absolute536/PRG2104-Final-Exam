package my.game.wl.model

class Player (_name: String, _healthPoint: Int, _damage: Int, var point: Int) extends Entity(_name, _healthPoint, _damage) {
  def this() = {this("Player", 3, 1, 0)}

  def addScore(enemy: Enemy): Unit = {
    point += enemy.stat.pointValue
  }
}