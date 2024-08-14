package my.game.wl.view

import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.MainApp


@sfxml
class MainMenuController {

  def startGame(): Unit = {
    val difficulty = getClass.getResource("Difficulty.fxml")
    val loader = new FXMLLoader(difficulty, NoDependencyResolver)
    loader.load()
    val difficultySelect = loader.getRoot[jfxs.layout.BorderPane]
    MainApp.roots.setCenter(difficultySelect)
  }

  def handleHelp(): Unit = {
    val defaultPage = getClass.getResource("Help.fxml")
    val loader = new FXMLLoader(defaultPage, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.BorderPane]
    MainApp.roots.setCenter(roots)
  }
  def showScoreBoard(): Unit = {
    val score = getClass.getResource("ScoreBoard.fxml")
    val loader = new FXMLLoader(score, NoDependencyResolver)
    loader.load()
    val scoreboard = loader.getRoot[jfxs.layout.BorderPane]
    MainApp.roots.setCenter(scoreboard)
  }

  def showSettings(): Unit = {
    val setting = getClass.getResource("Settings.fxml")
    val loader = new FXMLLoader(setting, NoDependencyResolver)
    loader.load()
    val content = loader.getRoot[jfxs.layout.BorderPane]
    MainApp.roots.setCenter(content)
  }

  def handleExit(): Unit = {
    System.exit(0)
  }
}