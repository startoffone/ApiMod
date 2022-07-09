package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Gold extends AbstractOre {
    public static final String ID = ModHelper.makeId(Gold.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Gold() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.SELF);
        this.setupMagicNumber(10);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainGoldAction(this.magicNumber));
    }
}
