package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;

public class Diamond extends AbstractOre {
    public static final String ID = ApiMod.makeID("Diamond");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Diamond() {
        super(ID, true, CARD_STRINGS, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ENEMY);
        this.setupBlock(5);
        this.setupMagicNumber(1);
        this.tags.add(Enums.Ore_Iron);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeBlock(3);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        applyToPlayer(new BlurPower(p,this.magicNumber));
    }
}
