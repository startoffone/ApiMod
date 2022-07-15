package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SilverFish extends AbstractOre {
    public static final String ID = ApiMod.makeID("SilverFish");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public SilverFish() {
        super(ID, true, CARD_STRINGS, CardType.STATUS, CardTarget.NONE);
        this.cost=-2;//更改默认0费
        this.exhaust = false;//更改默认消耗
        this.isEthereal=true;
        this.tags.add(Enums.Ore_Stone);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void limitedUpgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
