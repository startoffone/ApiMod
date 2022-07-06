package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Sand extends AbstractOre {
    public static final String ID = ModHelper.makeId(Sand.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Sand() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.ENEMY);
        this.setupMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToMonster(m,new VulnerablePower(m,this.magicNumber,false));//给予易伤
    }
    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }
}
