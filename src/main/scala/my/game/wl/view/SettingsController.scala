package my.game.wl.view

import javafx.collections.ObservableList
import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.beans.property.StringProperty
import scalafx.scene.control.{Label, Toggle, ToggleGroup}
import my.game.wl.util.Sound
import my.game.wl.MainApp


@sfxml
class SettingsController (
                         private val backgroundMusic: ToggleGroup
                         ) {

  def exitSettings(): Unit = {
    MainApp.showMainMenu()
  }

  def toggleBackgroundMusic(): Unit = {
    // CHANGE THIS
    backgroundMusic.selectedToggle.onChange(MainApp.s.stopBackgroundMusic())
  }
}