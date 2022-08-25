package ApiMod.power;

import ApiMod.core.ApiMod;
import ApiMod.power.abstractPowers.AbstractPowers;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;

public class OpeningLightningPower extends AbstractPowers {
    public static final String PowerID = ApiMod.makeID("OpeningLightningPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);

    public OpeningLightningPower(AbstractCreature Owner, int Damage) {
        this.ID = PowerID;
        this.name = powerStrings.NAME;
        this.type = PowerType.BUFF;
        this.owner = Owner;
        this.amount = Damage;
        this.useTmpArt();
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(powerStrings.DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        addToBot(new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount,
                DamageInfo.DamageType.THORNS), SLASH_HORIZONTAL));
    }
}
