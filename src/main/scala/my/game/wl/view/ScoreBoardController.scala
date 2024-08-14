package my.game.wl.view

import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.MainApp

@sfxml
class ScoreBoardController {
  def handleExitScoreBoard(): Unit = {
    MainApp.showMainMenu()
  }
}