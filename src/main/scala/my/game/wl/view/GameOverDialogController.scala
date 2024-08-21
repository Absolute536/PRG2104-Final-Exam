package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import my.game.wl.model.Player
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, TextField}
import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.Stage

@sfxml
class GameOverDialogController (
                               private val gameOverImage: ImageView,
                               private val playerNameField: TextField
                               ) {

  gameOverImage.image = new Image(getClass.getResource("../../../../images/GameOverImg.png").toString)

  var dialogStage: Stage = null
  private var _player: Player = null
  var quitClicked = false

  def player = _player
  def player_=(p: Player) {
    _player = p

    playerNameField.text = "Player"
  }


  def saveAndQuit(): Unit = {
    if (validatePlayerName()) {
      _player.name <== playerNameField.text
      MainApp.game.recordScore()
      quitClicked = true
      dialogStage.close()
      MainApp.game.scoreBoard.refreshEntries()
      MainApp.showMainMenu()
    }
  }

  private def validatePlayerName(): Boolean = {
    var errorMessage = ""
    if (playerNameField.text.value == null || playerNameField.text.value.isEmpty) {
      errorMessage = "No Player Name entered."
    }

    if (errorMessage.isEmpty) {
      true
    }
    else {
      val alert = new Alert(AlertType.Error) {
        initOwner(dialogStage)
        title = "Invalid Player Name"
        headerText = "Please enter a player name to save your score"
        contentText = errorMessage
      }.showAndWait()
      false
    }
  }

}