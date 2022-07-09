package ApiMod.action;

import ApiMod.cards.ore.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;
import java.util.Random;

public class DigOre extends AbstractGameAction {
    public DigOre(AbstractCreature Owner, int amount) {
        this.target = Owner;
        this.amount = amount;

    }

    @Override
    public void update() {
        //判断武器类型
        int range = 5;
        if (checkName("铁镐")) {
            range = 11;
        } else if (checkName("钻石镐")) {
            range = 12;
        }

        for (int i = 0; i < this.amount; i++) {
            getOre(range);
        }
        this.isDone = true;
    }

    //在手牌中加入矿石
    protected void getOre(int range) {
        Random random = new Random();

        int pickaxe = random.nextInt(range);

        switch (pickaxe) {
            case 0:
                this.addToBot(new MakeTempCardInHandAction(new Coal()));
                break;
            case 1:
                this.addToBot(new MakeTempCardInHandAction(new Fluorite()));
                break;
            case 2:
                this.addToBot(new MakeTempCardInHandAction(new Sand()));
                break;
            case 3:
                this.addToBot(new MakeTempCardInHandAction(new Stone()));
                break;
            case 4:
                this.addToBot(new MakeTempCardInHandAction(new SilverFish()));
                break;
            case 5:
                this.addToBot(new MakeTempCardInHandAction(new Iron()));
                break;
            case 6:
                this.addToBot(new MakeTempCardInHandAction(new Gold()));
                break;
            case 7:
                this.addToBot(new MakeTempCardInHandAction(new Diamond()));
                break;
            case 8:
                this.addToBot(new MakeTempCardInHandAction(new Quartz()));
                break;
        }
    }

    //通过名字确认能力
    protected boolean checkName(String name) {
        Iterator<AbstractPower> var2 = this.target.powers.iterator();
        AbstractPower p;
        do {
            if (!var2.hasNext()) {
                return false;
            }
            p = var2.next();
        } while (!p.name.equals(name));
        return true;
    }
}
