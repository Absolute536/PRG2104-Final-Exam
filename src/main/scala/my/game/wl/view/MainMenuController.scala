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
    val resource = getClass.getResource("HelpLayout.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.BorderPane]
    MainApp.roots.setCenter(roots)

    val defaultPage = getClass.getResource("HelpPage1.fxml")
    val pageLoader = new FXMLLoader(defaultPage, NoDependencyResolver)
    pageLoader.load()
    val helpPage = pageLoader.getRoot[jfxs.layout.BorderPane]
    roots.setCenter(helpPage)

  }
  def showScoreBoard(): Unit = {

  }

  def showSettings(): Unit = {

  }

  def showCredit(): Unit = {

  }
}