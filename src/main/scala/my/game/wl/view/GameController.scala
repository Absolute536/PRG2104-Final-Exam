package my.game.wl.view

import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.scene.layout.{AnchorPane, BorderPane, Pane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Line
import scalafx.util.Duration
import javafx.{scene => jfxs}
import scalafx.scene.text.{Text, TextFlow}

import java.util.{Timer, TimerTask}
import scala.collection.mutable.ListBuffer
import scala.util.Random

@sfxml
class GameController (
                       private val stageContainer: BorderPane,
                       private val stage: Pane,
                       private val defenseLine: Line,
                       private val playerSprite: ImageView,
                       private val enemy: VBox,
                       private val word: TextFlow
                     ) {

  // Add listener to stage's height and width to allow for proper displacement of defense line (100% of the height)
  stage.height.onChange((_, _, newHeight) => {
    defenseLine.endY = newHeight.doubleValue()
    playerSprite.layoutY = newHeight.doubleValue() * 0.50
    enemy.layoutY = newHeight.doubleValue() * 0.50
  })
  stage.width.onChange((_, _, newWidth) => {
    enemy.layoutX = newWidth.doubleValue() * 0.90
  })

  enemy.translateX.onChange((_, _, changedTranslateX) =>
    if (changedTranslateX.doubleValue() <= -stage.width.value) {
      MainApp.timer.cancel()
      Platform.runLater(() => showGameOver())
    })

  def displayWord(): Unit = {
    for (c <- MainApp.game.wordSelector.generateWord()) {
      word.children.add(new Text(c.toString))
    }
  }
  displayWord()


  val tTask = new TimerTask {
      override def run(): Unit = {
        enemy.translateX.value -= stage.width.value / 15
        println("Running")
      }
    }
  MainApp.timer.schedule(tTask, 500, 1000)

  var correctChar: Int = 0
  def wordListener(keyEvent: KeyEvent): Unit = {
    println(keyEvent.character)



    if (word.children(correctChar).asInstanceOf[jfxs.text.Text].text.value == keyEvent.character) {
      word.children(correctChar).asInstanceOf[jfxs.text.Text].fill = jfxs.paint.Color.RED
      correctChar += 1
    }

    if (correctChar == word.children.length) {
      correctChar = 0
      word.children.clear()
      displayWord()

    }


  }
  // display the pause dialog
  def showPauseDialog(keyEvent: KeyEvent): Unit = {

    if (keyEvent.code == KeyCode.Escape) {
      tTask.cancel()
      val pauseAlert = new Alert(AlertType.Confirmation) {
        title = "Pause Menu"
        headerText = "Paused"
        contentText = "The Game is Paused. Do you want to quit?"

      }.showAndWait()

      pauseAlert.get match {
        case ButtonType.OK => {
          MainApp.timer.cancel()
          MainApp.showMainMenu()
        }
        case _ =>
          MainApp.timer.schedule(new TimerTask {
            override def run(): Unit = {
              enemy.translateX.value -= stage.width.value / 15
              println("Running")
            }
          }, 0, 1000)
          println("Resume")

      }
    }
  }

  // Another dialog for input of player name
  def showGameOver(): Unit = {

    val gameOverAlert = new Alert(AlertType.Warning) {
      title = "Game Over"
      headerText = "Game Over"
      contentText = "The game is over"
    }.showAndWait()

  }











}