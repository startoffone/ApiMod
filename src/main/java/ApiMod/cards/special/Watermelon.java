package ApiMod.cards.special;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Watermelon extends AbstractCards {
    public static final String ID = ApiMod.makeID("Watermelon");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private final AbstractCards card;
    public Watermelon() {
        super(ID, true, CARD_STRINGS, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.setupMagicNumber(2);
        this.exhaust=true;
        this.card=new WatermelonSeed(true);
        this.cardsToPreview=card;
    }
    public Watermelon(boolean temp) {
        super(ID, true, CARD_STRINGS, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.setupMagicNumber(2);
        this.card=null;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(this.magicNumber);
        AbstractCards c=new WatermelonSeed();
        if (this.upgraded) {
            c.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(c,1,true,true));
    }

    @Override
    public void limitedUpgrade() {
        if (this.card!=null) {
            this.card.upgrade();
        }
        this.upgradeDescription(CARD_STRINGS);
    }
}
