package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Coal extends AbstractOre {
    public static final String ID = ApiMod.makeID("Coal");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Coal() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.SELF);
        this.setupBlock(5);
        this.tags.add(Enums.Ore_Stone);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {gainBlock();}

    @Override
    public void limitedUpgrade() {
        this.upgradeBlock(3);
    }
}
