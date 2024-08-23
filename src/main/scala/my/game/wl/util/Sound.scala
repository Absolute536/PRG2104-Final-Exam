package my.game.wl.util

import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.util.Duration

class Sound {
  // Property to store the selected settings of the Player
  val selectedSettings: Array[Int] = Array(0, 0, 0)
  val backgroundMusic: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/BackgroundMusic.mp3").toString))
  val soundEffect: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/Gunshot.wav").toString))
  // Current typing sound selected
  var currentTypingSound: Media = new Media(getClass.getResource("../../../../media/KeySound1.mp3").toString)
  // The collection of the typing sounds used in the game
  val typingSounds: Array[Media] = Array(
    new Media(getClass.getResource("../../../../media/KeySound1.mp3").toString),
    new Media(getClass.getResource("../../../../media/KeySound2.mp3").toString),
    new Media(getClass.getResource("../../../../media/KeySound3.mp3").toString)
  )
  backgroundMusic.autoPlay = true
  backgroundMusic.cycleCount = MediaPlayer.Indefinite
  backgroundMusic.volume = 0.75

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
    soundEffect.volume = 0.5
  }

  def setTypingSound(num: Int): Unit = {
    currentTypingSound = typingSounds(num)
  }

  def playTypingSound(): Unit = {
    // Instantiate a new MediaPlayer to for each typing sound played to ensure 0 delay
    val typingSound = new MediaPlayer(currentTypingSound)
    typingSound.volume = 3.0
    typingSound.startTime = new Duration(500)
    typingSound.seek(Duration.Zero)
    typingSound.play()
  }

}