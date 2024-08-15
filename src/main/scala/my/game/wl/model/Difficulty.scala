package my.game.wl.model

case class Difficulty (state: String, multiplier: Double)

case object Difficulty {
  val easy = Difficulty("Easy", 0.85)
  val medium = Difficulty("Medium", 1.00)
  val hard = Difficulty("Hard", 1.25)
}