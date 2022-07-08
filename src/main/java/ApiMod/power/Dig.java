package ApiMod.power;

import ApiMod.action.DigOre;
import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Dig extends Power {
    private static final String ID = ModHelper.makeId(Dig.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public Dig(AbstractCreature Owner, int Amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, Owner, Amount);
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
