package ApiMod.power.abstractPowers;

import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class Weapon extends AbstractPowers {
    public Weapon() {
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
       if (card.hasTag(Enums.Weapon)){
           addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
       }
    }
}
