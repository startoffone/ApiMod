package ApiMod.cards.weapon;

import ApiMod.cards.abstractCards.AbstractWeapon;
import ApiMod.core.ApiMod;
import ApiMod.power.Repeater30_30Power;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Repeater30_30 extends AbstractWeapon {
    public static final String ID = ApiMod.makeID("Repeater30_30");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Repeater30_30() {
        super(ID, true, CARD_STRINGS, 2, CardRarity.UNCOMMON);
    }

    @Override
    public void limitedUpgrade() {
       upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new Repeater30_30Power(p));
    }
}
