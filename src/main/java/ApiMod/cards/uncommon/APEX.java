package ApiMod.cards.uncommon;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import ApiMod.power.abstractPowers.Weapon;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class APEX extends AbstractCards {
    public static final String ID = ApiMod.makeID("APEX");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public APEX() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        setupMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof Weapon) {
                addToBot(new RemoveSpecificPowerAction(p, p, power));
                if (this.upgraded) {
                    int cost = ((Weapon) power).cost;
                    addToBot(new GainEnergyAction(cost));
                }
                break;
            }
        }

        drawCards(this.magicNumber);
    }

    @Override
    public void limitedUpgrade() {
        upgradeDescription(CARD_STRINGS);
    }
}
