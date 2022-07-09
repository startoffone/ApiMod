package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SilverFish extends AbstractOre {
    public static final String ID = ModHelper.makeId(SilverFish.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public SilverFish() {
        super(ID, true, CARD_STRINGS, CardType.STATUS, CardTarget.NONE);
        this.cost=-2;
        this.exhaust = false;
        this.isEthereal=true;
        this.upgraded=true;
    }

    @Override
    public void limitedUpgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
