package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.beans.property.StringProperty
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}

@sfxml
class HelpController (
                       private val helpTitle: Label,
                       private val helpImage: ImageView,
                       private val helpDesc: Label
                     ) {

  val helpTitles: Array[String] = Array("Game Rule:", "How to Play:", "Difficulty Guide:")

  val helpDescriptions: Array[String] = Array(
    "Defend against enemy attacks. You lose health point(s) when an enemy crosses the defense line. The game is over once your lives reach 0.",
    "Type the words above the enemies to shoot them. You will target the front-most enemy for the first matching character entered. Press the \"Tab\" key to deselect the target.",
    "Easy: 0.75 score multiplier\nMedium: 1.00 score multiplier\nHard: 1.25 score multiplier")

  val helpImages: Array[Image] = Array(
    new Image(getClass.getResource("../../../../images/G.png").toString),
    new Image(getClass.getResource("../../../../images/Screenshot (19).png").toString),
    new Image(getClass.getResource("../../../../images/Screenshot (23).png").toString)
  )

  def handleExitHelp(): Unit = {
    MainApp.showMainMenu()
  }

  def previousHelpPage(): Unit = {
    if (helpTitle.text.value != helpTitles(0)) {
      if (helpTitle.text.value == helpTitles(1)) {
        helpTitle.setText(helpTitles(0))
        helpDesc.setText(helpDescriptions(0))
        helpImage.image = helpImages(0)
      }
      else {
        helpTitle.setText(helpTitles(1))
        helpDesc.setText(helpDescriptions(1))
        helpImage.image = helpImages(1)
      }
    }
  }

  def nextHelpPage(): Unit = {
    if (helpTitle.text.value != helpTitles(2)) {
      if (helpTitle.text.value == helpTitles(0)) {
        helpTitle.setText(helpTitles(1))
        helpDesc.setText(helpDescriptions(1))
        helpImage.image = helpImages(1)
      }
      else {
        helpTitle.setText(helpTitles(2))
        helpDesc.setText(helpDescriptions(2))
        helpImage.image = helpImages(2)
      }
    }
  }

}
