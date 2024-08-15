package my.game.wl.view

import javafx.beans.property.SimpleIntegerProperty
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import my.game.wl.MainApp
import scalafx.scene.control.{TableColumn, TableView}
import my.game.wl.model.Score
import scalafx.beans.property.ObjectProperty

import scala.util.{Failure, Success, Try}
import java.util.Comparator

@sfxml
class ScoreBoardController (
                           private val scoreBoard:TableView[Score],
                           private val playerNameColumn: TableColumn[Score, String],
                           private val scorePointsColumn: TableColumn[Score, Int]
                           ) {

  if (MainApp.scoreBoard.isEmpty) {
    println("The file is empty")
  }
  else {
    scoreBoard.items = MainApp.scoreBoard.sortBy(player => player.points.value)
    playerNameColumn.cellValueFactory = {_.value.playerName}
    scorePointsColumn.cellValueFactory = {_.value.points}

  }

  def handleExitScoreBoard(): Unit = {
    MainApp.showMainMenu()
  }

}