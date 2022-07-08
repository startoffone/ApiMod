package ApiMod.power;

import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
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
        switch (this.name) {
            case "石镐":
                this.description = this.description + String.format(DESCRIPTIONS[1], 2);
            case "铁镐":
                this.description = this.description + String.format(DESCRIPTIONS[2], 2);
                break;
            case "钻石镐":
                this.description = this.description + String.format(DESCRIPTIONS[3], 3);
                break;
        }
    }
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
    }
}
