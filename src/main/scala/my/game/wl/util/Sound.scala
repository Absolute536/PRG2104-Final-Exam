package my.game.wl.util

import scalafx.scene.media.{Media, MediaPlayer}

class Sound {
  val backgroundMusic: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test.mp3").toString))
  val soundEffect: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test2.mp3").toString))
  var typingSound: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test2.mp3").toString))
  val typingSounds: Array[Media] = Array(
    new Media(getClass.getResource("../../../../media/test2.mp3").toString),
    new Media(getClass.getResource("../../../../media/test2.mp3").toString),
    new Media(getClass.getResource("../../../../media/test2.mp3").toString)
  )
  backgroundMusic.autoPlay = true

  def playBackgroundMusic(): Unit = {
    backgroundMusic.play()
    backgroundMusic.cycleCount = MediaPlayer.Indefinite
  }

  def stopBackgroundMusic(): Unit = {
    backgroundMusic.stop()
  }

  def playSoundEffect(): Unit = {
    soundEffect.play()
  }

  def disableSoundEffect(): Unit = {
    soundEffect.volume = 0.0
  }

  def enableSoundEffect(): Unit = {
    soundEffect.volume = 1.0
  }

  def setTypingSound(num: Int): Unit = {
    typingSound = new MediaPlayer(typingSounds(num))
  }

  def playTypingSound(): Unit = {
    typingSound.play()
  }

}