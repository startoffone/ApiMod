package ApiMod.action.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DiscoverCardsAction extends AbstractGameAction {
    private final Consumer<AbstractCard> callback;
    private final String text;
    private final boolean anyNumber;
    private final ArrayList<AbstractCard> cards;

    public DiscoverCardsAction(ArrayList<AbstractCard> Cards, String text, boolean anyNumber ,Consumer<AbstractCard> callback) {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.callback = callback;
        this.text = text;
        this.anyNumber=anyNumber;
        this.cards = Cards;

    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.cards.isEmpty() || this.callback == null) {
                this.isDone = true;
                return;
            }

            if (this.cards.size() == 1 && !this.anyNumber) {
                this.callback.accept(this.cards.get(0));
                this.isDone = true;
                return;
            }
            AbstractDungeon.cardRewardScreen.customCombatOpen(this.cards,this.text,this.anyNumber);
        }

        if (AbstractDungeon.cardRewardScreen.discoveryCard!=null) {
            this.callback.accept(AbstractDungeon.cardRewardScreen.discoveryCard);
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }
}
