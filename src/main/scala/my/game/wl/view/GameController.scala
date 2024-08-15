package my.game.wl.view

import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import javafx.{scene => jfxs}
import my.game.wl.model.Enemy
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.{BorderPane, Pane}
import scalafx.scene.shape.Line

import java.util.{Timer, TimerTask}
import scala.collection.mutable.ListBuffer

@sfxml
class GameController (
                     private val stageContainer: BorderPane,
                     private val stage: Pane,
                     private val defenseLine: Line,
                     private val playerSprite: ImageView,
                     private val enemySprites: ListBuffer[ImageView]
                     ) {

  // Add listener to stage's height and width to allow for proper displacement of defense line (15% of the width)
  stage.height.onChange((_, _, newHeight) => {
    defenseLine.endY = newHeight.doubleValue()
  })
  stage.width.onChange((_, _, newWidth) => {
    defenseLine.startX = newWidth.doubleValue() * 0.15
    defenseLine.endX = newWidth.doubleValue() * 0.15
  })

  playerSprite.image = new Image(getClass.getResource("../../../../images/New Piskel(1).gif").toString)

  def testBtn(): Unit = {
    MainApp.game.enemies += Enemy.slime
  }

  def stageFocus(): Unit = {
    stage.requestFocus()
  }
  def showPauseDialog(keyEvent: KeyEvent): Unit = {
    println("Pressed")

    if (keyEvent.code == KeyCode.Escape) {
      val pauseAlert = new Alert(AlertType.Confirmation) {
        title = "Pause Menu"
        headerText = "Paused"
        contentText = "The Game is Paused. Do you want to quit?"

      }.showAndWait()

      pauseAlert.get match {
        case ButtonType.OK => MainApp.showMainMenu()
        case _ => println("Resume")

      }
    }
  }






}