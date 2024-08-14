package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.ToggleGroup

@sfxml
class DifficultyController (
                           private val difficultyChoice: ToggleGroup
                           ) {

  def exitToMainMenu(): Unit = {
    MainApp.showMainMenu()
  }

  def startGame(): Unit = {

  }
}