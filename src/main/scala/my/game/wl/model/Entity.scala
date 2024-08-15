package my.game.wl.model

abstract class Entity (val name: String, var healthPoint: Int, val damage: Int) {

  def dealDamage(damagedParty: Entity, dmg: Int): Unit = {
    damagedParty.receiveDamage(dmg)
  }

  private def receiveDamage(dmg: Int): Unit = {
    healthPoint = healthPoint - dmg
  }
}
