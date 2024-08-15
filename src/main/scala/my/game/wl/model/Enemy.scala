package my.game.wl.model

import my.game.wl.util.WordSelector
import scala.util.Random

class Enemy(val stat: EnemyStat, val word: String) extends Entity(stat.name, stat.healthPoint, stat.damage) {
  override def dealDamage(damagedParty: Entity, dmg: Int): Unit = {
    super.dealDamage(damagedParty, dmg)
  }
}

object Enemy {
  def spawn(time: Long): Enemy = {
    val wordSelect = new WordSelector
    val timeElapsed = (System.currentTimeMillis() / 1000L) - (time / 1000L)

    if (timeElapsed < 30) {
      new Enemy(EnemyStat.normalSlime, wordSelect.generateWord())
    }
    else {
      val r = Random.nextInt(5)
      r match {
        case 0 => new Enemy(EnemyStat.normalSlime, wordSelect.generateWord())
        case 1 => new Enemy(EnemyStat.strongSlime, wordSelect.generateWord())
        case 2 => new Enemy(EnemyStat.rockSlime, wordSelect.generateWord())
        case 3 => new Enemy(EnemyStat.waterSlime, wordSelect.generateWord())
        case 4 => new Enemy(EnemyStat.fireSlime, wordSelect.generateWord())
      }
    }
  }
}