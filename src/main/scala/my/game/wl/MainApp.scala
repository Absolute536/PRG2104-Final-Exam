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
import scalafx.stage.{Modality, Stage}
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

  def showGameOverDialog(player: Player): Boolean = {
    // normally we get getResource and get the url object
    // here, we use getResourceAsStream, and then when you pass it to loader, you need to
    // indicate a null field
    val resource = getClass.getResourceAsStream("view/GameOverDialog.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    // root is referring to the UI itself
    val roots2  = loader.getRoot[jfxs.Parent]
    // then we get the controller
    // we need to use a #, as the controller we created is not a "real" controller (because we use code injection @sfxml)-
    val control = loader.getController[GameOverDialogController#Controller]

    // Stage & Primary Stage
    // In one application, you can only have one primary stage
    // to create a sub-window, you need to create a Stage
    val dialog = new Stage() {
      // we want to set it at the top, so we use Modality
      // so that the sub-window will be at the top
      initModality(Modality.ApplicationModal)
      initOwner(stage)
      // show the scene, and define the root
      scene = new Scene {
        // second stylesheet here
        root = roots2
      }
    }
    // dialogStage & person in the controller is null, so they must be initialised
    control.dialogStage = dialog
    control.player = player
    dialog.showAndWait() // this will pop-up the window (it's a blocking function, meaning that the execution will pause until the user responds)
    control.quitClicked // the function return a Boolean, so we use the method in the controller class
  }









}