package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
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
    "Defend against enemy attack. The enemy will march towards you continuously, survive for as long as possible. The game is over once the enemy crosses the defense line (red).",
    "Type the words generated above the enemy to shoot and knock-back the enemy. A sound cue will play upon correctly typed character, and the character will turn red",
    "Easy: 3 ~ 4 letter words. Each correct word typed gives 20 points.\nMedium: 5 letter words. Each correct word typed gives 35 points.\nHard: 5 ~ 6 letter words. Each correct word typed gives 50 points.")

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
