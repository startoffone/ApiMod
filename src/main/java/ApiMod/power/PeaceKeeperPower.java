package ApiMod.power;

import ApiMod.core.ApiMod;
import ApiMod.power.abstractPowers.Weapon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;

public class PeaceKeeperPower extends Weapon {
    public static final String PowerID = ApiMod.makeID("PeaceKeeperPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);
    private final AbstractPlayer p;
    private int damage, strAdd;
    private final int times;
    private boolean hasAttack;

    public PeaceKeeperPower(AbstractPlayer P, int Damage, int Times,int Cost) {
        super(Cost);
        this.ID = PowerID;
        this.name = powerStrings.NAME;
        this.owner = P;
        this.useTmpArt();

        this.p = P;
        this.damage = Damage;
        this.times = Times;
        //初始判断本回合是否打出攻击牌，打出则本回合不翻倍
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                hasAttack = true;
                break;
            }
        }
    }

    @Override
    public void updateDescription() {
        if (this.damage<0) {
            this.description = String.format(powerStrings.DESCRIPTIONS[0], 0, this.times);
        }else {
            this.description = String.format(powerStrings.DESCRIPTIONS[0], this.damage, this.times);
        }
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        //打出攻击牌，移除自身
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (!hasAttack && this.p.hasPower("Strength")) {
                int str = (this.p.getPower("Strength")).amount;
                this.strAdd += str;
                addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, str)));
            } else {
                this.hasAttack = false;
            }
        }
    }

    @Override
    public void onRemove() {
        //先打出伤害，后移除力量加成
        for (int i = 0; i < this.times; i++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(this.owner, this.damage,
                    DamageInfo.DamageType.NORMAL), SLASH_HORIZONTAL));
        }
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -this.strAdd)));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        //更新力量时，更新移除的伤害描述
        if (this.p.hasPower("Strength")) {
            this.damage += this.p.getPower("Strength").amount;
        }
        this.updateDescription();
    }
}
