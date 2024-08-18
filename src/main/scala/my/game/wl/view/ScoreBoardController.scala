package my.game.wl.view

import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import scalafx.scene.control.{TableColumn, TableView}
import my.game.wl.model.Score

@sfxml
class ScoreBoardController (
                           private val scoreBoard:TableView[Score],
                           private val playerNameColumn: TableColumn[Score, String],
                           private val scorePointsColumn: TableColumn[Score, Int],
                           private val difficultyColumn: TableColumn[Score, String]
                           ) {

  if (MainApp.game.scoreBoard.scoreEntries.isEmpty) {
    println("The file is empty")
  }
  else {
    scoreBoard.items = MainApp.game.scoreBoard.scoreEntries
    playerNameColumn.cellValueFactory = {_.value.playerName}
    scorePointsColumn.cellValueFactory = {_.value.points}
    difficultyColumn.cellValueFactory = {_.value.difficulty}
  }

  def handleExitScoreBoard(): Unit = {
    MainApp.showMainMenu()
  }

}