package my.game.wl.view

import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.MainApp
import scalafx.beans.property
import scalafx.beans.property.StringProperty
import scalafx.scene.layout._
import scalafx.scene.text.Text
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}

@sfxml
class HelpController (
                       private val helpTitle: Label,
                       private val helpImage: ImageView,
                       private val helpDesc: Label
                     ) {

  private val currentHelpTitle: StringProperty = new StringProperty(helpTitle.getText)
  helpTitle.text <== currentHelpTitle
  private val currentDesc: StringProperty = new StringProperty(helpDesc.getText) // retrieve current help description when the controller is initialised
  helpDesc.text <== currentDesc


  val helpTitles: Array[String] = Array("Game Rules: ", "How to Play: ", "Difficulty Guide: ")
  val helpDescriptions: Array[String] = Array(
    "Defend against enemy attacks. You lose health point(s) when an enemy crosses the defense line. The game is over once your lives reach 0.",
    "Type the words above the enemies to shoot them. You will target the front-most enemy for the first matching character entered. Press the \"Tab\" key to deselect the target.",
    "Easy: 0.75 score multiplier\nMedium: 1.00 score multiplier\nHard: 1.25 score multiplier")

  // Hmm, maybe we can put these in case class?

  def handleExitHelp(): Unit = {
    MainApp.showMainMenu()
  }

  def previousHelpPage(): Unit = {
    if (currentHelpTitle.value != helpTitles(0)) {
      if (currentHelpTitle.value == helpTitles(1)) {
        currentHelpTitle.setValue(helpTitles(0))
        currentDesc.setValue(helpDescriptions(0))
      }
      else {
        currentHelpTitle.setValue(helpTitles(1))
        currentDesc.setValue(helpDescriptions(1))
      }
    }
  }

  def nextHelpPage(): Unit = {
    if (currentHelpTitle.value != helpTitles(2)) {
      if (currentHelpTitle.value == helpTitles(1)) {
        currentHelpTitle.setValue(helpTitles(2))
        currentDesc.setValue(helpDescriptions(2))
      }
      else {
        currentHelpTitle.setValue(helpTitles(1))
        currentDesc.setValue(helpDescriptions(1))
      }
    }
  }

  /*
  def previousHelpPage(): Unit = {
    val helpPageId: String = MainApp.roots.getCenter.getId
    val helpPageNum: Int = helpPageId.charAt((helpPageId.length - 1)).toInt - '1'.toInt + 1
    if (helpPageNum > 1) {
      println(helpPageId)
      val resource = getClass.getResource(s"HelpPage${helpPageNum - 1}.fxml")
      val loader = new FXMLLoader(resource, NoDependencyResolver)
      loader.load()
      val roots = loader.getRoot[jfxs.layout.BorderPane]
      MainApp.roots.setCenter(roots)
    }

  }

  def nextHelpPage(): Unit = {
    val helpPageId: String = MainApp.roots.getCenter.getId
    val helpPageNum: Int = helpPageId.charAt((helpPageId.length - 1)).toInt - '1'.toInt + 1
    println(helpPageId)
    if (helpPageNum < 4) {
      println(helpPageId)
      println(helpPageNum)
      val resource = getClass.getResource(s"HelpPage${helpPageNum + 1}.fxml")
      val loader = new FXMLLoader(resource, NoDependencyResolver)
      loader.load()
      val roots = loader.getRoot[jfxs.layout.BorderPane]
      MainApp.roots.setCenter(roots)
    }

  }

   */

}
