package my.game.wl.util

import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.util.Duration

class Sound {
  val backgroundMusic: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test.mp3").toString))
  val soundEffect: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/Gunshot.wav").toString))
  var currentTypingSound: Media = new Media(getClass.getResource("../../../../media/KeySound1.mp3").toString)
  val typingSounds: Array[Media] = Array(
    new Media(getClass.getResource("../../../../media/KeySound1.mp3").toString),
    new Media(getClass.getResource("../../../../media/KeySound2.mp3").toString),
    new Media(getClass.getResource("../../../../media/KeySound3.mp3").toString)
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
    soundEffect.seek(Duration.Zero)
    soundEffect.play()
  }

  def disableSoundEffect(): Unit = {
    soundEffect.volume = 0.0
  }

  def enableSoundEffect(): Unit = {
    soundEffect.volume = 0.50
  }

  def setTypingSound(num: Int): Unit = {
    currentTypingSound = typingSounds(num)
  }

  def playTypingSound(): Unit = {
    val typingSound = new MediaPlayer(currentTypingSound)
    typingSound.volume = 2.0
    typingSound.startTime = new Duration(500)
    typingSound.seek(Duration.Zero)
    typingSound.play()
  }

}