package ApiMod.action;

import ApiMod.patches.Enums;
import ApiMod.power.PickaxePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class DigOre extends AbstractGameAction {
    private ArrayList<AbstractCard> list;

    public DigOre(AbstractCreature Owner, int amount) {
        this.target = Owner;
        this.amount = amount;

    }

    @Override
    public void update() {
        if (checkPickaxe()) {
            getOre();
        } else {
            for (AbstractCard c : AbstractDungeon.srcColorlessCardPool.group) {
                if (c.hasTag(Enums.Ore_Stone)) {
                    this.list.add(c);
                }
                getOre();
            }
        }
        this.isDone = true;
    }

    protected void getOre() {
        for (int i = 0; i < this.amount; i++) {
            int temp = AbstractDungeon.cardRng.random(this.list.size() - 1);
            addToBot(new MakeTempCardInHandAction(this.list.get(temp)));
        }
    }

    //通过名字确认能力
    protected boolean checkPickaxe() {
        PickaxePower p = (PickaxePower)AbstractDungeon.player.getPower("ApiMod:PickaxePower");
        if (p != null) {
            this.list=p.cardsList;
            return true;
        }
        return false;
    }
}
