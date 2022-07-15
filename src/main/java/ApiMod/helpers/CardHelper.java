package ApiMod.helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;


public interface CardHelper {
    interface Lambda extends Runnable {
    }

    //匿名函数添加action
    static void addToBottom(Lambda func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    static void addToTop(Lambda func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    //战斗中根据tag返回卡牌数组
    static ArrayList<AbstractCard> returnTrulyRandomCardInCombat(AbstractCard.CardTags tag, int amount) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        ArrayList<AbstractCard> returnCard = new ArrayList<>();

        for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
            if (c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
            if (c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
            if (c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }
        for (int i = 0; i < amount; i++) {
            int temp = AbstractDungeon.cardRng.random(list.size() - 1);
            returnCard.add(list.get(temp));
            list.remove(temp);
        }
        return returnCard;
    }
}
