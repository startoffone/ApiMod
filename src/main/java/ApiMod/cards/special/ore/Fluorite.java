package ApiMod.cards.special.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Fluorite extends AbstractOre {
    public static final String ID = ApiMod.makeID("Fluorite");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Fluorite() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.ENEMY);
        this.setupMagicNumber(2);
        this.tags.add(Enums.Ore_Stone);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToMonster(m,new WeakPower(m,this.magicNumber,false));//给予虚弱
    }
    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
    }
}
