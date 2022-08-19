package ApiMod.cards.rare;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import ApiMod.power.Dig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HerringBoneStopping extends AbstractCards {
    public static final String ID = ApiMod.makeID("HerringBoneStopping");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public HerringBoneStopping() {
        super(ID, true, CARD_STRINGS, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.setupMagicNumber(1);
    }


    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new Dig(this.magicNumber));
    }
}
