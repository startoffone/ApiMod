package ApiMod.cards.common;

import ApiMod.cards.abstractCards.AbstractCard;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Throw extends AbstractCard {
    public static final String ID = ApiMod.makeID("Throw");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Throw() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(3);
    }

    @Override
    public void limitedUpgrade() {
        upgradeBaseCost(0);
        upgradeMagicNumber(-1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAction(1,false));
        drawCards(this.magicNumber);
    }
}
