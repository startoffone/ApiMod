package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Stone extends AbstractOre {
    public static final String ID = ApiMod.makeID("Stone");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Stone() {
        super(ID, true, CARD_STRINGS, CardType.POWER, CardTarget.SELF);
        this.setupBlock(3);
        this.setupMagicNumber(1);
        this.tags.add(Enums.Ore_Stone);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        applyToPlayer(new StrengthPower(p, this.magicNumber));//获得力量
        if (this.upgraded) {
            applyToPlayer(new DexterityPower(p, this.magicNumber));//获得敏捷
        }
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeDescription(CARD_STRINGS);
    }
}
