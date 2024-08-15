package my.game.wl.model

case class EnemyStat(name: String, healthPoint: Int, damage: Int, pointValue: Int, speed: Double)

object EnemyStat {
  val normalSlime = EnemyStat("Normal Slime", 1, 1, 20, 1.0)
  val strongSlime = EnemyStat("Strong Slime", 2, 1, 30, 0.9)
  val rockSlime = EnemyStat("Rock Slime", 3, 1, 50, 0.75)
  val waterSlime = EnemyStat("Water Slime", 1, 1, 35, 1.25)
  val fireSlime = EnemyStat("Fire Slime", 1, 2, 45, 0.9)
}