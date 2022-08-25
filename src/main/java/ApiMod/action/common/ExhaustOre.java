package ApiMod.action.common;

import ApiMod.core.ApiMod;
import ApiMod.helpers.CardHelper;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class ExhaustOre extends AbstractGameAction {
    private final AbstractPlayer p;

    private final boolean anyNumber;
    public static int numExhausted;

    public static final String ID = ApiMod.makeID("ExhaustOre");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private final CardHelper.Lambda func;

    public ExhaustOre(int amount, CardHelper.Lambda func) {
        this(amount, false, func);
    }

    public ExhaustOre(int amount, boolean anyNumber, CardHelper.Lambda func) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.anyNumber = anyNumber;
        this.func = func;
    }


    @Override
    public void update() {
        ArrayList<AbstractCard> noOreList = new ArrayList<>();
        ArrayList<AbstractCard> oreList = new ArrayList<>();

        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard card : this.p.hand.group) {
                if (card.hasTag(Enums.Ore)) {
                    oreList.add(card);
                } else {
                    noOreList.add(card);
                }
            }
            int oreAmount = oreList.size();

            if (oreAmount == 0 | oreAmount < this.amount) {
                this.isDone = true;
                return;
            }

            if (!this.anyNumber && this.amount == oreAmount) {
                numExhausted = oreAmount;
                oreList.forEach(c -> AbstractDungeon.player.hand.moveToExhaustPile(c));
                this.func.run();
                this.isDone = true;
                return;
            }

            this.p.hand.group.removeAll(noOreList);
            AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], this.amount, this.anyNumber);
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractDungeon.handCardSelectScreen.selectedCards.group.forEach(this.p.hand::moveToExhaustPile);
            this.func.run();
            noOreList.forEach(this.p.hand::addToTop);
            numExhausted = AbstractDungeon.handCardSelectScreen.selectedCards.size();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        this.isDone = true;
    }

}
