package my.game.wl.model

abstract class Entity (var healthPoint: Int, val damage: Int) {

  def dealDamage(damagedParty: Entity, dmg: Int): Unit = {
    damagedParty.receiveDamage(dmg)
  }

  def receiveDamage(dmg: Int): Unit = {
    healthPoint = healthPoint - dmg
  }
}