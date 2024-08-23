package my.game.wl.view

import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType, Label}
import scalafx.scene.image.ImageView
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{Pane, VBox}
import scalafx.scene.shape.Line
import javafx.{scene => jfxs}
import scalafx.beans.property.IntegerProperty
import scalafx.scene.text.{Text, TextFlow}

import java.util.{Timer, TimerTask}


@sfxml
class GameController (
                       private val gameDefenseArea: Pane,
                       private val gameStage: Pane,
                       private val defenseLine: Line,
                       private val playerSprite: ImageView,
                       private val enemySprite: VBox,
                       private val word: TextFlow,
                       private val score: Label
                     ) {
  // Move the sprites to the front
  enemySprite.toFront()
  playerSprite.toFront()
  // Ensuring the defense area scales correctly with height
  /* Add listener to stage's height and width to allow for:
      proper scaling of defense line (100% of height)
      proper positioning of sprites
  */
  gameStage.height.onChange((_, _, newHeight) => {
    defenseLine.endY = newHeight.doubleValue()
    gameDefenseArea.prefHeight = newHeight.doubleValue()
    playerSprite.layoutY = newHeight.doubleValue() * 0.40
    enemySprite.layoutY = newHeight.doubleValue() * 0.30
  })
  gameStage.width.onChange((_, _, newWidth) => {
    enemySprite.layoutX = newWidth.doubleValue() * 0.85
  })

  // Listener to check for the game over condition (based on the TranslateX of the enemy sprite
  enemySprite.translateX.onChange((_, _, changedTranslateX) =>
    if (changedTranslateX.doubleValue() <= -gameStage.width.value + 150) {
      MainApp.timer.cancel()
      Platform.runLater(() => showGameOver())
    })

  // Score displayed on the game scene (bind to the player's points property)
  val currentScore: IntegerProperty = IntegerProperty(0)
  currentScore <== MainApp.game.player.points

  // Schedule the movement of the enemy sprite to the Timer upon loading the scene & controller
  val moveEnemy = new TimerTask {
    override def run(): Unit = {
      enemySprite.translateX.value -= (gameStage.width.value - 150) / 15
    }
  }
  MainApp.timer.schedule(moveEnemy, 500, 1300)

  // Update the display of the generated word
  private def refreshWord(): Unit = {
    for (character <- MainApp.game.word) {
      word.children.add(new Text(character.toString))
    }
  }
  refreshWord()

  // Tracker variable that points to the current character the player needs to type
  var currentCharacter: Int = 0
  def validateCharacterTyped(keyEvent: KeyEvent): Unit = {

    // Play typing sound and colour the character red if the character typed is correct
    if (MainApp.game.word(currentCharacter).toString == keyEvent.character) {
      MainApp.game.sound.playTypingSound()
      word.children(currentCharacter).asInstanceOf[jfxs.text.Text].fill = jfxs.paint.Color.RED
      currentCharacter += 1 // also move the pointer/tracker one position forward
    }

    // Check if the whole word is correctly typed
    // Generate another word, update score and enemy knockback if true
    if (currentCharacter == word.children.length) {
      word.children.clear()
      MainApp.game.nextWord()
      refreshWord()
      currentCharacter = 0
      MainApp.game.player.increasePoints(MainApp.game.difficulty)
      enemySprite.translateX.value += 30 * ((gameStage.width.value - 150) / 650) // 650 is the initial width of the gameStage (- width of defense area)
      score.text = s"Score: ${currentScore.value.toString}"
      MainApp.game.sound.playSoundEffect()
    }
  }

  // display the pause dialog
  def showPauseDialog(keyEvent: KeyEvent): Unit = {

    if (keyEvent.code == KeyCode.Escape) {
      // Cancel timer upon pressing ESC
      MainApp.timer.cancel()
      val pauseAlert = new Alert(AlertType.Confirmation) {
        title = "Paused"
        headerText = "Game Paused"
        contentText = "The Game is Paused.\nDo you want to go back to the Main Menu?"
      }.showAndWait()


      pauseAlert.get match {
        // Return to main menu if OK, clear the word list
        case ButtonType.OK => {
          MainApp.game.wordSelector.clearWordList()
          MainApp.showMainMenu()
        }
        case _ =>
          // Assign another Timer to the timer in MainApp to allow resume of the game if CANCEL
          MainApp.timer = new Timer(true)
          MainApp.timer.schedule(new TimerTask {
            override def run(): Unit = {
              enemySprite.translateX.value -= (gameStage.width.value - 150) / 15
            }
          }, 0, 1300)
      }
    }
  }

  // Another dialog for input of player name
  def showGameOver(): Unit = {
    MainApp.game.wordSelector.clearWordList()
    MainApp.showGameOverDialog(MainApp.game.player)
  }

}