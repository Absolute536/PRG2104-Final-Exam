package my.game.wl

import scalafx.application.JFXApp
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.application.JFXApp._
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object MainApp extends JFXApp {
  val mainMenu = getClass.getResource("view/MainMenu.fxml")
  val loader = new FXMLLoader(mainMenu, NoDependencyResolver)
  loader.load()
  val main = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Typer Defense"
    scene = new Scene {
      root = main
    }
  }

  def showHelp(): Unit = {
    val resource = getClass.getResource("view/Help.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load
    val roots = loader.getRoot[jfxs.layout.BorderPane]
    this.main.setCenter(roots)
  }

}