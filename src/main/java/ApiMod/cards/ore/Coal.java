package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;

import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Coal extends AbstractOre {
    public static final String ID = ModHelper.makePath(Coal.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Coal() {
        super(ID, true, CARD_STRINGS, CardType.SKILL,CardTarget.SELF);
        this.setupBlock(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.limitedUpgrade();
        this.upgradeBlock(3);
        this.upgradeDescription(CARD_STRINGS);
    }
}
