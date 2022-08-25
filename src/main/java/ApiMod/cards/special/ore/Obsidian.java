package ApiMod.cards.special.ore;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HEAVY;

public class Obsidian extends AbstractOre {
    public static final String ID = ApiMod.makeID("Obsidian");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Obsidian() {
        super(ID, true, CARD_STRINGS, CardType.ATTACK, CardTarget.SELF_AND_ENEMY);
        setupDamage(15);
        setupBlock(15);
        this.tags.add(Enums.Ore_Diamond);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeDamage(5);
        this.upgradeBlock(5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, SLASH_HEAVY);
        gainBlock();
    }
}
