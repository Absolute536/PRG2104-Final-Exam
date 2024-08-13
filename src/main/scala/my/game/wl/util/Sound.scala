package my.game.wl.util

import scalafx.scene.media.{Media, MediaPlayer}

class Sound {
  val backgroundMusic: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test.mp3").toString))
  val soundEffect: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test2.mp3").toString))
  var typingSound: MediaPlayer = new MediaPlayer(new Media(getClass.getResource("../../../../media/test2.mp3").toString))


  def playBackgroundMusic(): Unit = {
    backgroundMusic.autoPlay = true
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

  def setTypingSound(sound: Media): Unit = {
    typingSound = new MediaPlayer(sound)
  }

  def playTypingSound(): Unit = {
    typingSound.play()
  }

}