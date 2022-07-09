package ApiMod.power;

import com.megacrit.cardcrawl.core.AbstractCreature;

public abstract class Weapon extends Power {
    public Weapon() {
       this.type=PowerType.BUFF;
    }

}
