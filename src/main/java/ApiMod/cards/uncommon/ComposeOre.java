package ApiMod.cards.uncommon;

import ApiMod.action.ComposeOreAction;
import ApiMod.cards.abstractCards.AbstractCard;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComposeOre extends AbstractCard {
    public static final String ID = ApiMod.makeID("ComposeOre");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public ComposeOre() {
        super(ID, true, CARD_STRINGS, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        setupMagicNumber(2);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ComposeOreAction(this.magicNumber));
    }
}
