package my.game.wl.util

import my.game.wl.model.Score
import scalafx.collections.ObservableBuffer

import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter}

class ScoreBoard {
  var scoreEntries: ObservableBuffer[Score] = new ObservableBuffer[Score]()

  // Read TopScore.txt to collection
  def initialiseEntries(): Unit = {
    val reader = new BufferedReader(new FileReader(new File(getClass.getResource("../../../../TopScore.txt").toURI)))
    var line = reader.readLine()
    while (line != null && line.nonEmpty) {
      var entries = line.split(",") // Each column is seperated by comma (like CSV)
      scoreEntries += new Score(entries(0), entries(1).toInt, entries(2))
      line = reader.readLine()
    }
    reader.close()

    // Sort the entries by player score in descending order
    scoreEntries = scoreEntries.sortBy(s => s.points.value).reverse
    // Take only the Top 50 scores
    scoreEntries = scoreEntries.take(50)
  }

  // Write scores to TopScore.txt
  def updateEntriesToFile(score: Score): Unit = {
    val fWriter = new FileWriter(new File(getClass.getResource("../../../../TopScore.txt").toURI), true)
    val bWriter = new BufferedWriter(fWriter)
    scoreEntries += score
    bWriter.write(s"${score.playerName.value},${score.points.value.toString},${score.difficulty.value}\n")
    bWriter.flush()
    bWriter.close()
  }

  // Method to refresh the ordering of scoreboard, especially when a new record is added (during game over)
  def refreshEntries(): Unit = {
    scoreEntries = scoreEntries.sortBy(s => s.points.value).reverse
  }

}