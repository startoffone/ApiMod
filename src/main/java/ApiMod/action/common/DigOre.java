package ApiMod.action.common;

import ApiMod.helpers.GetPool;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class DigOre extends AbstractGameAction {

    public DigOre( int amount) {
        this.amount = amount;

    }

    @Override
    public void update() {
        ArrayList<AbstractCard> list = new GetPool().pickaxePool();
        for (int i = 0; i < this.amount; i++) {
            int temp = AbstractDungeon.cardRng.random(list.size() - 1);
            addToBot(new MakeTempCardInHandAction(list.get(temp)));
        }
        this.isDone = true;
    }
}
