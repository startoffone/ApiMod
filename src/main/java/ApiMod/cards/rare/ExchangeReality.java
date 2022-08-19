package ApiMod.cards.rare;

import ApiMod.action.ExchangeRealityAction;
import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExchangeReality extends AbstractCards {
    public static final String ID = ApiMod.makeID("ExchangeReality");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public ExchangeReality() {
        super(ID, true, CARD_STRINGS, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.exhaust=true;
    }

    @Override
    public void limitedUpgrade() {
        this.selfRetain = true;
        rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExchangeRealityAction());
    }
}
