package my.game.wl.view

import scalafx.Includes._
import scalafxml.core.macros.sfxml
import my.game.wl.MainApp
import javafx.{scene => jfxs}
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{BorderPane, Pane}
import scalafx.scene.shape.Line

import scala.collection.mutable.ListBuffer

@sfxml
class GameController (
                     private val stageContainer: BorderPane,
                     private val stage: Pane,
                     private val defenseLine: Line,
                     private val playerSprite: ImageView,
                     private val enemySprites: ListBuffer[ImageView]
                     ) {

  // Add listener to stage's height and width to allow for proper displacement of defense line (15% of the width)
  stage.height.onChange((_, _, newHeight) => {
    defenseLine.endY = newHeight.doubleValue()
  })
  stage.width.onChange((_, _, newWidth) => {
    defenseLine.startX = newWidth.doubleValue() * 0.15
    defenseLine.endX = newWidth.doubleValue() * 0.15
  })

  playerSprite.image = new Image(getClass.getResource("../../../../images/New Piskel(1).gif").toString)








}