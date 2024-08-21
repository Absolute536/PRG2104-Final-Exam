package my.game.wl.model

case class Difficulty (mode: String, scorePoint: Int)

case object Difficulty {
  val easy = Difficulty("Easy", 20)
  val medium = Difficulty("Medium", 35)
  val hard = Difficulty("Hard", 50)
}