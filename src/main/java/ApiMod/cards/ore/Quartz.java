package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Quartz extends AbstractOre {
    public static final String ID = ModHelper.makeId(Quartz.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Quartz() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.SELF);
        this.setupMagicNumber(3);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p,p,this.magicNumber));
    }
}
