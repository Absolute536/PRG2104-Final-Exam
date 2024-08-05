package my.game.wl.view

import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.MainApp

@sfxml
class MainMenuController {
  def startGame(): Unit = {

  }

  def selectDifficulty(): Unit = {

  }

  def showHelp():Unit = {
    val resource = getClass.getResource("Help.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load
    val roots = loader.getRoot[jfxs.layout.BorderPane]
    MainApp.roots.setCenter(roots)
  }
  def showScoreBoard(): Unit = {

  }

  def showSettings(): Unit = {

  }

  def showCredit(): Unit = {

  }
}