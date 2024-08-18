package my.game.wl

import scalafx.application.JFXApp
import javafx.{scene => jfxs}
import my.game.wl.model.Game
import scalafx.Includes._
import scalafx.application.JFXApp._
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.model._
import java.util.Timer


object MainApp extends JFXApp {

  val rootResource = getClass.getResource("view/Root.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Typer Defense"
    scene = new Scene {
      root = roots
    }
  }

  def showMainMenu(): Unit = {
    val mainMenu = getClass.getResource("view/MainMenu.fxml")
    val loader = new FXMLLoader(mainMenu, NoDependencyResolver)
    loader.load()
    val main = loader.getRoot[jfxs.layout.BorderPane]
    this.roots.setCenter(main)

  }
  showMainMenu()

  val game = new Game(new Player)

  var timer = new Timer(true)

  def showGame(): Unit = {
    val gameView = getClass.getResource("view/Game.fxml")
    val loader = new FXMLLoader(gameView, NoDependencyResolver)
    loader.load()
    val gameStage = loader.getRoot[jfxs.layout.BorderPane]
    this.roots.setCenter(gameStage)

  }









}