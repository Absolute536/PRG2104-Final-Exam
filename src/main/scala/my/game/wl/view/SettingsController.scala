package my.game.wl.view

import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.ToggleGroup


@sfxml
class SettingsController (
                         private val backgroundMusic: ToggleGroup,
                         private val soundEffect: ToggleGroup,
                         private val typingSound: ToggleGroup
                         ) {
  // Method to ensure the selected state of settings will persist after switching scenes
  private def initialiseSettings(): Unit = {
    backgroundMusic.selectToggle(backgroundMusic.toggles(MainApp.game.sound.selectedSettings(0)))
    soundEffect.selectToggle(soundEffect.toggles(MainApp.game.sound.selectedSettings(1)))
    typingSound.selectToggle(typingSound.toggles(MainApp.game.sound.selectedSettings(2)))
  }
  initialiseSettings()

  def selectBackgroundMusic(): Unit = {
    if (backgroundMusic.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "On") {
      MainApp.game.sound.playBackgroundMusic()
      MainApp.game.sound.selectedSettings(0) = 0
    }
    else {
      MainApp.game.sound.stopBackgroundMusic()
      MainApp.game.sound.selectedSettings(0) = 1
    }
  }

  def selectSoundEffect(): Unit = {
    if (soundEffect.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "On") {
      MainApp.game.sound.enableSoundEffect()
      MainApp.game.sound.selectedSettings(1) = 0
    }
    else {
      MainApp.game.sound.disableSoundEffect()
      MainApp.game.sound.selectedSettings(1) = 1
    }
  }

  def selectTypingSound(): Unit = {
    if (typingSound.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "Typing Sound 1") {
      MainApp.game.sound.setTypingSound(0)
      MainApp.game.sound.selectedSettings(2) = 0
    }
    else if (typingSound.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "Typing Sound 2") {
      MainApp.game.sound.setTypingSound(1)
      MainApp.game.sound.selectedSettings(2) = 1
    }
    else {
      MainApp.game.sound.setTypingSound(2)
      MainApp.game.sound.selectedSettings(2) = 2
    }
  }

  def exitSettings(): Unit = {
    MainApp.showMainMenu()
  }

}