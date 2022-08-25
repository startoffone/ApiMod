package ApiMod.action.common;

import ApiMod.core.ApiMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SelectCardToHand extends AbstractGameAction {
    public static final String ID = ApiMod.makeID("SelectCardToHand");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private final ArrayList<AbstractCard> cards;
    private  boolean isUpgrade, useGrid,anyNumber;
    private  Consumer<AbstractCard> func;


    public SelectCardToHand(ArrayList<AbstractCard> cards,boolean anyNumber) {
        this(cards,anyNumber,false);
    }

    public SelectCardToHand(ArrayList<AbstractCard> cards,boolean anyNumber, boolean isUpgrade) {
        this.cards = cards;
        this.isUpgrade=isUpgrade;
        this.anyNumber=anyNumber;
    }

    public SelectCardToHand(ArrayList<AbstractCard> cards, int amount, Consumer<AbstractCard> func) {
        this.cards = cards;
        this.amount = amount;
        this.useGrid = true;
        this.func=func;
    }

    @Override
    public void update() {
        if (this.useGrid) {
            Consumer<List<AbstractCard>> callBack = c -> {
                AbstractCard card = c.get(0);
                this.cards.remove(card);
                func.accept(card);
                if (this.isUpgrade) {
                    card.upgrade();
                }
                addToBot(new MakeTempCardInHandAction(card));
            };
            addToBot(new SelectCardsAction(cards, this.amount, uiStrings.TEXT[0], callBack));
        } else {
            Consumer<AbstractCard> callBack=c -> addToBot(new MakeTempCardInHandAction(c));
            addToBot(new DiscoverCardsAction(cards, uiStrings.TEXT[0], this.anyNumber,callBack));
        }
        this.isDone = true;
    }

}
