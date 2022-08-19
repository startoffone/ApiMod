package ApiMod.power;

import ApiMod.core.ApiMod;
import ApiMod.helpers.CardHelper;
import ApiMod.power.abstractPowers.Weapon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;

public class PeaceKeeperPower extends Weapon {
    public static final String PowerID = ApiMod.makeID("PeaceKeeperPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);
    private final AbstractPlayer p;
    private final int damage, times;
    private int strAdd = 0;

    public PeaceKeeperPower(AbstractPlayer p, int damage, int times) {
        this.ID = PowerID;
        this.name = powerStrings.NAME;
        this.owner = p;
        this.useTmpArt();

        this.p = p;
        this.damage = damage;
        this.times = times;
    }

    @Override
    public void updateDescription() {
        this.description = String.format(powerStrings.DESCRIPTIONS[0], this.damage, this.times);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            boolean noAttack = true;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    noAttack = false;
                    break;
                }
            }
            if (noAttack && this.p.hasPower("Strength")) {
                int str = (this.p.getPower("Strength")).amount;
                this.strAdd += str;
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, str)));
            }
        }
    }

    @Override
    public void onRemove() {
        for (int i = 0; i < this.times; i++) {
            CardHelper.addToBottom(() -> {
                AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage), SLASH_HORIZONTAL));
            });
        }
        CardHelper.addToBottom(() -> addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -this.strAdd))));

    }
}
