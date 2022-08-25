package ApiMod.cards.uncommon;

import ApiMod.action.common.ExhaustOre;
import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.cards.special.ore.Gold;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GoldenTouch extends AbstractCards {
    public static final String ID = ApiMod.makeID("GoldenTouch");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public GoldenTouch() {
        super(ID, true, CARD_STRINGS, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.setupMagicNumber(3);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustOre(this.magicNumber, true,()->{
            AbstractOre Gold = new Gold();
            if (this.upgraded) {
                Gold.upgrade();
            }
            addToBot(new MakeTempCardInHandAction(new Gold(), ExhaustOre.numExhausted));
        }));
    }
}
