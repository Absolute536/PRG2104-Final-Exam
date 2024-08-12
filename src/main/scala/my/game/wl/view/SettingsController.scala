package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.beans.property.StringProperty
import scalafx.scene.control.Label

@sfxml
class SettingsController {

  def exitSettings(): Unit = {
    MainApp.showMainMenu()
  }
}