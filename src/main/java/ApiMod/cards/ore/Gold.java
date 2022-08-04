package ApiMod.cards.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL;

public class Gold extends AbstractOre {
    public static final String ID = ApiMod.makeID("Gold");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public Gold() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.SELF);
        this.setupDamage(6);
        this.tags.add(Enums.Ore_Iron);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeDamage(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m,SLASH_DIAGONAL);
        addToBot(new GainGoldAction(this.damage));
    }
}
