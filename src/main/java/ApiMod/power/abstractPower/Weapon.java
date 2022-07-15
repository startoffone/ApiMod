package ApiMod.power.abstractPower;

import ApiMod.power.abstractPower.Power;

public abstract class Weapon extends Power {
    public Weapon() {
        this.amount = -1;
        this.type = PowerType.BUFF;
    }

}
