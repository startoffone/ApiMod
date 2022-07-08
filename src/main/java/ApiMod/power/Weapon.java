package ApiMod.power;

import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Weapon extends Power {
    private static final String ID = ModHelper.makeId(Weapon.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Weapon(AbstractCreature Owner, String NAME) {
        super(ID, NAME, PowerType.BUFF, Owner);
    }

    @Override
    public void updateDescription() {
        //将武器名字拼接进描述
        this.description = String.format(DESCRIPTIONS[0], this.name);
        int weaponType = ModHelper.checkWeapon(this.name);
        if (weaponType != 0) {
            this.description = this.description + String.format(DESCRIPTIONS[weaponType], weaponType);
        }
    }

    //增加能力时触发
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        //武器牌唯一，判断是否使用武器
        if (power.ID.equals(ID)) {
            int weaponType = ModHelper.checkWeapon(this.name);
            //取消之前的能力
            if (weaponType<4) {
                addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, "ApiMod:Dig", weaponType));
                this.name = power.name;//更新能力名称，为描述的更新定位
            }
        }
    }
}
