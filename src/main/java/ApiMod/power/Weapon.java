package ApiMod.power;

import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Weapon extends Power {
    private static final String ID = ModHelper.makeId(Weapon.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Logger logger = LogManager.getLogger(Weapon.class.getName());

    public Weapon(AbstractCreature Owner, String NAME) {
        super(ID, NAME, PowerType.BUFF, Owner);

    }

    @Override
    public void updateDescription() {
        //将武器名字拼接进描述
        this.description = String.format(DESCRIPTIONS[0], this.name);
        int weaponType = checkWeapon();
        if (weaponType != 0) {
            this.description = this.description + String.format(DESCRIPTIONS[weaponType], weaponType);
        }


    }

    //增加能力时触发
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        //武器牌唯一，判断是否使用武器
        if (power.ID.equals(ID)) {
            String name = power.name;
            //取消之前的能力
            if (checkWeapon()<4) {
                pickaxeReduce(name, checkWeapon());
            }
        }
    }

    protected void pickaxeReduce(String name, int amount) {
        addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, "ApiMod:Dig", amount));
        this.name = name;//更新能力名称，为描述的更新定位
    }

    private int checkWeapon() {
        switch (this.name) {
            case "石镐":
                return 1;
            case "铁镐":
                return 2;
            case "钻石镐":
                return 3;
            default:
                logger.info("[ERROR]WeaponType: " + this.name + " not found");
                return 0;
        }
    }

}
