package my.game.wl.view

import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.beans.property.StringProperty
import scalafx.scene.control.{Label, RadioButton, Toggle, ToggleGroup}
import my.game.wl.util.Sound
import my.game.wl.MainApp


@sfxml
class SettingsController (
                         private val backgroundMusic: ToggleGroup,
                         private val soundEffect: ToggleGroup,
                         private val keyboardSound: ToggleGroup
                         ) {

  def exitSettings(): Unit = {
    MainApp.showMainMenu()
  }

  def toggleBackgroundMusic(): Unit = {
    println(backgroundMusic.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value)
    if (backgroundMusic.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "On") {
      MainApp.s.playBackgroundMusic()
    }
    else {
      MainApp.s.stopBackgroundMusic()
    }
  }

  def toggleSoundEffect(): Unit = {
    if (soundEffect.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "On") {
      MainApp.s.playSoundEffect()
    }
    else {
      MainApp.s.disableSoundEffect()
    }
  }

  def toggleKeyboardSound(): Unit = {
    if (keyboardSound.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "Typing Sound 1") {
      MainApp.s.setTypingSound(0)
    }
    else if (keyboardSound.getSelectedToggle.asInstanceOf[jfxs.control.RadioButton].text.value == "Typing Sound 2") {
      MainApp.s.setTypingSound(1)
    }
    else {
      MainApp.s.setTypingSound(2)
    }
  }

}