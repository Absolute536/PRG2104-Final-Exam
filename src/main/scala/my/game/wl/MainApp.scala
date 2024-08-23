package my.game.wl

import scalafx.application.JFXApp
import javafx.{scene => jfxs}
import my.game.wl.model.Game
import scalafx.Includes._
import scalafx.application.JFXApp._
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.model._
import my.game.wl.view.GameOverDialogController
import scalafx.scene.image.Image
import scalafx.stage.{Modality, Stage}

import java.util.Timer


object MainApp extends JFXApp {

  val rootResource = getClass.getResource("view/Root.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Typer Defense"
    icons += new Image(getClass.getResourceAsStream("../../../images/Icon.png"))
    scene = new Scene {
      root = roots
      stylesheets = List(getClass.getResource("view/GameTheme.css").toString)
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

  // Game object
  val game = new Game
  // Timer in the JFXApp thread to perform periodic execution of some codes
  var timer = new Timer(true)

  def showGame(): Unit = {
    val gameView = getClass.getResource("view/Game.fxml")
    val loader = new FXMLLoader(gameView, NoDependencyResolver)
    loader.load()
    val gameStage = loader.getRoot[jfxs.layout.BorderPane]
    this.roots.setCenter(gameStage)
  }

  // Reference: AddressApp Assignment from PRG2104 lab sessions (by Chin. T.M.)
  def showGameOverDialog(player: Player): Boolean = {
    val gameOver = getClass.getResourceAsStream("view/GameOverDialog.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(gameOver);
    val roots2  = loader.getRoot[jfxs.Parent]
    val control = loader.getController[GameOverDialogController#Controller]

    val dialog = new Stage() {
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      scene = new Scene {
        root = roots2
        stylesheets = List(getClass.getResource("view/GameTheme.css").toString)
      }
    }
    control.dialogStage = dialog
    control.player = player
    dialog.showAndWait()
    control.quitClicked
  }









}