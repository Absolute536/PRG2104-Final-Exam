package my.game.wl.model

class Player (_healthPoint: Int, _damage: Int, val playerName: String, var point: Int) extends Entity(_healthPoint, _damage) {



  def addScore(enemy: Enemy): Unit = {
    point += enemy.pointValue
  }
}