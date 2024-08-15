package my.game.wl.view

import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.ToggleGroup
import javafx.{scene => jfxs}
import my.game.wl.model.Difficulty

@sfxml
class DifficultyController (
                           private val difficultyChoice: ToggleGroup
                           ) {

  def exitToMainMenu(): Unit = {
    MainApp.showMainMenu()
  }

  def startGame(): Unit = {
    val selectedDifficulty = difficultyChoice.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton]

    selectedDifficulty.text.value match {
      case "Easy" => MainApp.game.difficulty = Difficulty.easy
      case "Medium" => MainApp.game.difficulty = Difficulty.medium
      case "Hard" => MainApp.game.difficulty = Difficulty.hard
    }

    MainApp.startGame()
    println(MainApp.game.difficulty.toString)
  }
}