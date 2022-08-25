package ApiMod.cards.uncommon;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import ApiMod.power.OpeningLightningPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OpeningLightning extends AbstractCards {
    public static final String ID = ApiMod.makeID("OpeningLightning");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public OpeningLightning() {
        super(ID, true, CARD_STRINGS, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        setupDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new OpeningLightningPower(p,this.damage));
    }
    @Override
    public void limitedUpgrade() {
        this.isInnate = true;
        this.upgradeDescription(CARD_STRINGS);
    }
}
