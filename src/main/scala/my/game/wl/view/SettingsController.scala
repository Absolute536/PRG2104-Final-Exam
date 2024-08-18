package my.game.wl.view

import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.{Label, RadioButton, Toggle, ToggleGroup}


@sfxml
class SettingsController (
                         private val backgroundMusic: ToggleGroup,
                         private val soundEffect: ToggleGroup,
                         private val typingSound: ToggleGroup
                         ) {

  def selectBackgroundMusic(): Unit = {
    if (backgroundMusic.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "On") {
      MainApp.game.sound.playBackgroundMusic()
    }
    else {
      MainApp.game.sound.stopBackgroundMusic()
    }
  }

  def selectSoundEffect(): Unit = {
    if (soundEffect.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "On") {
      MainApp.game.sound.enableSoundEffect()
    }
    else {
      MainApp.game.sound.disableSoundEffect()
    }
  }

  def selectTypingSound(): Unit = {
    if (typingSound.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "Typing Sound 1") {
      MainApp.game.sound.setTypingSound(0)
    }
    else if (typingSound.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "Typing Sound 2") {
      MainApp.game.sound.setTypingSound(1)
    }
    else {
      MainApp.game.sound.setTypingSound(2)
    }
  }

  def exitSettings(): Unit = {
    MainApp.showMainMenu()
  }

}