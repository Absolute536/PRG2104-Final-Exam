package my.game.wl

import scalafx.application.JFXApp
import javafx.{scene => jfxs}
import my.game.wl.model.Score
import scalafx.Includes._
import scalafx.application.JFXApp._
import scalafx.scene.Scene
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.util.Sound
import scalafx.collections.ObservableBuffer

import scala.io.Source
import scala.util.{Failure, Success, Try}

object MainApp extends JFXApp {

  val rootResource = getClass.getResource("view/Root.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Typer Slime"
    scene = new Scene {
      root = roots
    }
  }

  def showMainMenu(): Unit = {
    val mainMenu = getClass.getResource("view/MainMenu.fxml")
    val loader = new FXMLLoader(mainMenu, NoDependencyResolver)
    loader.load()
    val main = loader.getRoot[jfxs.layout.BorderPane]
    this.roots.setCenter(main)

  }
  showMainMenu()

  val s: Sound = new Sound()
  s.playBackgroundMusic()

  val scores = new ObservableBuffer[Score]()
  // Maybe we should move it to some other place
  val scoreBoardFile = Try({Source.fromFile(getClass.getResource("../../../TopScore.txt").toURI)})
  scoreBoardFile match {
    case Success(x) => for (line <- x.getLines().map(l => l.split(","))) {
      scores += new Score(line(0), line(1).toInt, line(2))
    }
      x.close()
    case Failure(e) => e.printStackTrace()
  }


}