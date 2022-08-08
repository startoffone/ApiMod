package ApiMod.power.abstractPowers;

public abstract class Weapon extends Power {
    public Weapon() {
        this.amount = -1;
        this.type = PowerType.BUFF;
    }

}
