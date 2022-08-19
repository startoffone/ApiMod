package ApiMod.power;

import ApiMod.action.common.DigOre;
import ApiMod.core.ApiMod;
import ApiMod.power.abstractPowers.AbstractPowers;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class Dig extends AbstractPowers {
    public static final String PowerID = ApiMod.makeID("Dig");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);

    public Dig( int Amount) {
        this.amount=Amount;
        this.ID=PowerID;
        this.name=powerStrings.NAME;

        this.useTmpArt();
        this.updateDescription();
    }
    // 重写能力更新时的修改描述方法
    @Override
    public void updateDescription() {
        this.description = String.format(powerStrings.DESCRIPTIONS[0], this.amount);
    }
    // 回合开始时，添加挖矿动作
    @Override
    public void atStartOfTurn() {
        this.addToBot(new DigOre(this.amount));
    }

}
