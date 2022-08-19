package ApiMod.cards.weapon;

import ApiMod.cards.abstractCards.AbstractWeapon;
import ApiMod.core.ApiMod;
import ApiMod.power.PeaceKeeperPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PeaceKeeper extends AbstractWeapon {
    public static final String ID = ApiMod.makeID("PeaceKeeper");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public PeaceKeeper() {
        super(ID, true, CARD_STRINGS, 1, CardRarity.COMMON);
        setupDamage(0);
        setupMagicNumber(9);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new PeaceKeeperPower(p,this.damage,this.magicNumber));
    }

    @Override
    public void limitedUpgrade() {
        upgradeDamage(1);
        upgradeMagicNumber(2);
    }
}
