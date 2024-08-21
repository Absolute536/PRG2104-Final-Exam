package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.BorderPane

@sfxml
class HelpController (
                       private val helpContent: BorderPane,
                       private val helpTitle: Label,
                       private val helpImage: ImageView,
                       private val helpDesc: Label,
                       private val prevButton: Button,
                       private val nextButton: Button
                     ) {

  helpContent.width.onChange((_, _, newWidth) => helpImage.fitWidth = newWidth.doubleValue() * 0.80)
  helpContent.height.onChange((_, _, newHeight) => helpImage.fitHeight = newHeight.doubleValue() * 0.75)

  val helpTitles: Array[String] = Array("Game Rule:", "How to Play:", "Difficulty Guide:")

  val helpDescriptions: Array[String] = Array(
    "Defend against enemy attack. The enemy will march towards you continuously, survive for as long as possible and achieve the highest score. The game is over once the enemy crosses the defense line (red).",
    "Type the words generated above the enemy to shoot and knock-back the enemy. The correctly typed character will turn red, and the typing sound will be played. Entering the whole word correctly to obtain points. \nPress \"Esc\" to pause the game",
    "Easy: 3 to 4 letter words. Each correct word typed gives 20 points.\nMedium: 5 letter words. Each correct word typed gives 35 points.\nHard: 6 to 7 letter words. Each correct word typed gives 50 points.")

  val helpImages: Array[Image] = Array(
    new Image(getClass.getResource("../../../../images/Help-Img1.png").toString),
    new Image(getClass.getResource("../../../../images/Help-Img2.png").toString),
    new Image(getClass.getResource("../../../../images/Help-Img3.png").toString)
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
        prevButton.disable = true
      }
      else {
        helpTitle.setText(helpTitles(1))
        helpDesc.setText(helpDescriptions(1))
        helpImage.image = helpImages(1)
        nextButton.disable = false
      }
    }
  }

  def nextHelpPage(): Unit = {
    if (helpTitle.text.value != helpTitles(2)) {
      if (helpTitle.text.value == helpTitles(0)) {
        helpTitle.setText(helpTitles(1))
        helpDesc.setText(helpDescriptions(1))
        helpImage.image = helpImages(1)
        prevButton.disable = false
      }
      else {
        helpTitle.setText(helpTitles(2))
        helpDesc.setText(helpDescriptions(2))
        helpImage.image = helpImages(2)
        nextButton.disable = true
      }
    }
  }

}
