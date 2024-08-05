package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp

@sfxml
class MainMenuController {
  def showHelp():Unit = {
    MainApp.showHelp()
  }
}