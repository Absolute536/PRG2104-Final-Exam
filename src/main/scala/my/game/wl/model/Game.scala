package my.game.wl.model

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer

class Game (
           val player: ObjectProperty[Player],
           val enemies: ObservableBuffer[Enemy],
           val score: ObjectProperty[Score]
           ) {


}
