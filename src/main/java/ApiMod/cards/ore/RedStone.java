package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedStone extends AbstractOre {
    public static final String ID = ApiMod.makeID("RedStone");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public RedStone() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.SELF);
        setupMagicNumber(1);
        this.tags.add(Enums.Ore_Iron);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(this.magicNumber);
    }
}
