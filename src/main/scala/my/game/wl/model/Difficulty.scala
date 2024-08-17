package my.game.wl.model

case class Difficulty (mode: String, multiplier: Double)

case object Difficulty {
  val easy = Difficulty("Easy", 1.00)
  val medium = Difficulty("Medium", 1.20)
  val hard = Difficulty("Hard", 1.50)
}