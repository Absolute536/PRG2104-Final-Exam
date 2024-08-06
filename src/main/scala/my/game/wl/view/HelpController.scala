package my.game.wl.view

import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.MainApp
import scalafx.scene.layout._

@sfxml
class HelpController {

  def helpPageId: String = MainApp.roots.getCenter.getId
  println("start: " + helpPageId)
  def handleExitHelp(): Unit = {
    MainApp.showMainMenu()
  }

  def previousHelpPage(): Unit = {
    println(helpPageId)
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

}
