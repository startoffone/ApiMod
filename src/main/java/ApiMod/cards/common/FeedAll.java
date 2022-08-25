package ApiMod.cards.common;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.cards.special.Watermelon;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FeedAll extends AbstractCards {
    public static final String ID = ApiMod.makeID("FeedAll");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private final AbstractCards card;
    public FeedAll() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.setupMagicNumber(2);
        this.exhaust=true;
        this.card=new Watermelon();
        this.cardsToPreview=card;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInDrawPileAction(this.card,this.magicNumber,true,true));
    }

    @Override
    public void limitedUpgrade() {
        card.upgrade();
        this.upgradeDescription(CARD_STRINGS);
    }
}
