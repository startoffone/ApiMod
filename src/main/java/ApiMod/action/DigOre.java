package ApiMod.action;

import ApiMod.cards.ore.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

import java.util.Random;

public class DigOre extends AbstractGameAction {
    public DigOre(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        for (int i = 0; i < this.amount; i++) {
            getOre();
        }
        this.isDone = true;
    }

    //在手牌中加入矿石
    protected void getOre() {
        Random random = new Random();
        int pickaxe = random.nextInt(4);//默认为石镐

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
        }
    }
}
