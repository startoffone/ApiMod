package ApiMod.power;

import ApiMod.core.ApiMod;
import ApiMod.helpers.CardHelper;
import ApiMod.power.abstractPowers.Weapon;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Repeater30_30Power extends Weapon {
    public static final String PowerID = ApiMod.makeID("Repeater30_30Power");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);

    private boolean doubleDamage;
    private final AbstractPlayer player;

    public Repeater30_30Power(AbstractPlayer p) {
        this.ID = PowerID;
        this.name = powerStrings.NAME;
        this.owner = player=p;
        this.useTmpArt();
    }

    @Override
    public void updateDescription() {
        this.description =powerStrings.DESCRIPTIONS[0];
    }

    @Override
    public void onRemove() {
        if (!player.hand.isEmpty()) {
            addToBot(new NewQueueCardAction(player.hand.getTopCard(),
                    true, false, true));
        }
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return card != player.hand.getBottomCard();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card == player.hand.getTopCard()) {
            this.doubleDamage = true;
        }
        super.onPlayCard(card, m);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL&&this.doubleDamage) {
            return damage * 2.0F;
        }
        return damage;
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        CardHelper.addToBottom(()-> doubleDamage = false);
    }
}
