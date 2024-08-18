package my.game.wl.view

import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.ToggleGroup
import javafx.{scene => jfxs}
import my.game.wl.model.Difficulty

import java.util.Timer

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
      case "Easy" => MainApp.game.difficulty.value = Difficulty.easy
      case "Medium" => MainApp.game.difficulty.value = Difficulty.medium
      case "Hard" => MainApp.game.difficulty.value = Difficulty.hard
    }
    println(MainApp.game.difficulty.value.mode)
    MainApp.timer = new Timer(true)
    MainApp.game.initialiseGame()
    MainApp.showGame()
  }
}