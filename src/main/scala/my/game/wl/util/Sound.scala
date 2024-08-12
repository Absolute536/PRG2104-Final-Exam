package my.game.wl.util

import scalafx.scene.media.{Media, MediaPlayer}

import java.net.URI

class Sound {

  def playBackgroundMusic(): Unit = {
    val mediaU: String = getClass.getResource("../../../../media/test.mp3").toString
    val media: Media = new Media(mediaU)
    val backgroundMusic = new MediaPlayer(media)
    backgroundMusic.autoPlay = true
    backgroundMusic.cycleCount = MediaPlayer.Indefinite
  }

}