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
import scalafx.animation.TranslateTransition
import scalafx.util.Duration

import java.util.{Timer, TimerTask}
import scala.collection.mutable.ListBuffer
import scala.util.Random

@sfxml
class GameController (
                     private val stageContainer: BorderPane,
                     private val stage: Pane,
                     private val defenseLine: Line,
                     private val playerSprite: ImageView,
                     private val enemy: ImageView,
                     ) {

  // Add listener to stage's height and width to allow for proper displacement of defense line (15% of the width)
  stage.height.onChange((_, _, newHeight) => {
    defenseLine.endY = newHeight.doubleValue()
    playerSprite.layoutY = newHeight.doubleValue() * 0.50
    enemy.layoutY = newHeight.doubleValue() * 0.50
  })
  stage.width.onChange((_, _, newWidth) => {
    defenseLine.startX = newWidth.doubleValue() * 0.15
    defenseLine.endX = newWidth.doubleValue() * 0.15
    enemy.layoutX = newWidth.doubleValue() * 0.90
  })

  val tt = new TranslateTransition(Duration(15000), enemy) {
    byX = -700

  }
  tt.play()



  // TO BE REMOVED
  def testBtn(): Unit = {
    MainApp.game.generateEnemy()
  }

  // mouse click to get focus on the game pane
  def stageFocus(): Unit = {
    stage.requestFocus()
  }
  // display the pause dialog
  def showPauseDialog(keyEvent: KeyEvent): Unit = {
    println("Pressed")

    if (keyEvent.code == KeyCode.Escape) {
      val pauseAlert = new Alert(AlertType.Confirmation) {
        title = "Pause Menu"
        headerText = "Paused"
        contentText = "The Game is Paused. Do you want to quit?"

      }.showAndWait()

      pauseAlert.get match {
        case ButtonType.OK => {
          MainApp.showMainMenu()
        }
        case _ => println("Resume")
      }
    }
  }

  MainApp.game.enemies.onChange((source, change) => {
    val e = new ImageView(new Image(getClass.getResource("../../../../images/G.png").toString))
    e.layoutX = 900
    val position_Y: Int = Random.nextInt(3)
    position_Y match {
      case 0 => e.layoutY = Random.nextInt((stage.height.value * 0.25).toInt)
      case 1 => e.layoutY = Random.nextInt((stage.height.value * 0.25).toInt) + stage.height.value * 0.25 + 50
      case 2 => e.layoutY = Random.nextInt((stage.height.value * 0.25).toInt) + stage.height.value * 0.50 + 100
    }
    stage.children.add(e)})



  // Key Type






}