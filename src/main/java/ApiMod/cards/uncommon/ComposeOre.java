package ApiMod.cards.uncommon;

import ApiMod.action.ComposeOreAction;
import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComposeOre extends AbstractCards {
    public static final String ID = ApiMod.makeID("ComposeOre");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public ComposeOre() {
        super(ID, true, CARD_STRINGS, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust=true;
    }

    @Override
    public void limitedUpgrade() {
        this.exhaust=false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ComposeOreAction(this.magicNumber));
    }
}
