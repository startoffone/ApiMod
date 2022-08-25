package ApiMod.cards.special;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WatermelonSeed extends AbstractCards {
    public static final String ID = ApiMod.makeID("WatermelonSeed");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private final AbstractCards card;
    public WatermelonSeed() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.setupMagicNumber(2);
        this.exhaust=true;
        this.card=new Watermelon(true);
        this.cardsToPreview=card;
    }

    public WatermelonSeed(boolean temp) {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.setupMagicNumber(2);
        this.card=null;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(this.magicNumber);
        AbstractCards c=new Watermelon();
        if (this.upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(c,2,true,true));
    }

    @Override
    public void limitedUpgrade() {
        if (this.card!=null) {
            this.card.upgrade();
        }
        this.isEthereal=true;
        this.upgradeDescription(CARD_STRINGS);
    }
}
