package ApiMod.cards.common;

import ApiMod.action.common.ExhaustOre;
import ApiMod.cards.abstractCards.AbstractCard;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Smelt extends AbstractCard {
    public static final String ID = ApiMod.makeID("Smelt");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Smelt() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(2);
    }

    @Override
    public void limitedUpgrade() {
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.upgradedMagicNumber = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustOre(this.magicNumber));
    }
}
