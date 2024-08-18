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
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalafx.scene.text.{Text, TextFlow}

import java.util.{Timer, TimerTask}
import scala.collection.mutable.ListBuffer
import scala.util.Random

@sfxml
class GameController (
                       private val gameStage: Pane,
                       private val defenseLine: Line,
                       private val playerSprite: ImageView,
                       private val enemySprite: VBox,
                       private val word: TextFlow,
                       private val score: Label
                     ) {

  // Add listener to stage's height and width to allow for proper displacement of defense line (100% of the height)
  gameStage.height.onChange((_, _, newHeight) => {
    defenseLine.endY = newHeight.doubleValue()
    playerSprite.layoutY = newHeight.doubleValue() * 0.50
    enemySprite.layoutY = newHeight.doubleValue() * 0.45
  })
  gameStage.width.onChange((_, oldWidth, newWidth) => {
    enemySprite.layoutX = newWidth.doubleValue() * 0.90
  })

  enemySprite.translateX.onChange((_, _, changedTranslateX) =>
    if (changedTranslateX.doubleValue() <= -gameStage.width.value) {
      MainApp.timer.cancel()
      Platform.runLater(() => showGameOver())
    })

  val currentScore: IntegerProperty = IntegerProperty(0)
  currentScore <== MainApp.game.player.points

  val tTask = new TimerTask {
    override def run(): Unit = {
      enemySprite.translateX.value -= gameStage.width.value / 15
    }
  }
  MainApp.timer.schedule(tTask, 500, 1300)


  private def refreshWord(): Unit = {
    for (character <- MainApp.game.word.value) {
      word.children.add(new Text(character.toString))
    }
  }
  refreshWord()

  var currentCharacter: Int = 0
  def validateCharacterTyped(keyEvent: KeyEvent): Unit = {
    if (MainApp.game.word.value(currentCharacter).toString == keyEvent.character) {
      MainApp.game.sound.playTypingSound()
      word.children(currentCharacter).asInstanceOf[jfxs.text.Text].fill = jfxs.paint.Color.RED
      currentCharacter += 1
    }

    if (currentCharacter == word.children.length) {
      word.children.clear()
      MainApp.game.nextWord()
      refreshWord()
      currentCharacter = 0
      MainApp.game.player.increasePoints(MainApp.game.difficulty.value)
      enemySprite.translateX.value += 40
      score.text = s"Score: ${currentScore.value.toString}"
      MainApp.game.sound.playSoundEffect()
    }
  }

  // display the pause dialog
  def showPauseDialog(keyEvent: KeyEvent): Unit = {

    if (keyEvent.code == KeyCode.Escape) {
      tTask.cancel()
      val pauseAlert = new Alert(AlertType.Confirmation) {
        title = "Pause Menu"
        headerText = "Paused"
        contentText = "The Game is Paused. Do you want to go back to the Main Menu?"

      }.showAndWait()

      pauseAlert.get match {
        case ButtonType.OK => {
          MainApp.timer.cancel()
          MainApp.game.wordSelector.clearWordList()
          MainApp.showMainMenu()
        }
        case _ =>
          MainApp.timer.schedule(new TimerTask {
            override def run(): Unit = {
              enemySprite.translateX.value -= gameStage.width.value / 15
              println("Running")
            }
          }, 0, 1300)
          println("Resume")

      }
    }
  }

  // Another dialog for input of player name
  def showGameOver(): Unit = {
    MainApp.game.wordSelector.clearWordList()
    MainApp.showGameOverDialog(MainApp.game.player)

  }











}