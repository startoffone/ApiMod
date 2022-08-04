package ApiMod.action.common;

import ApiMod.core.ApiMod;
import ApiMod.helpers.GetPool;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.List;
import java.util.stream.Collectors;

public class ExhaustOre extends AbstractGameAction {
    private AbstractPlayer p;
    private List<AbstractCard> noOreList;
    private final boolean anyNumber;
    public  int numExhausted;

    public static final String ID=ApiMod.makeID("ExhaustOre");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public ExhaustOre(int amount) {
        this(amount, false);
    }

    public ExhaustOre(int amount, boolean anyNumber) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.anyNumber = anyNumber;
    }


    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            noOreList = this.p.hand.group.stream().filter(card -> !card.hasTag(Enums.Ore)).collect(Collectors.toList());
            int oreAmount = this.p.hand.group.size() - noOreList.size();

            if (oreAmount == 0) {
                this.isDone = true;
                return;
            }
            if (this.anyNumber) {
                this.p.hand.group.removeAll(this.noOreList);
                AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], this.amount, true);
                tickDuration();
                return;
            } else {
                if (oreAmount == this.amount) {
                    noOreList = this.p.hand.group.stream().filter(card -> card.hasTag(Enums.Ore)).collect(Collectors.toList());
                    noOreList.forEach(c -> AbstractDungeon.player.hand.moveToExhaustPile(c));
                    CardCrawlGame.dungeon.checkForPactAchievement();
                    addToBot(new SelectCardToHand(new GetPool().returnRandomCardInCombat(3)));

                    tickDuration();
                    return;
                }
                if (oreAmount > this.amount) {
                    this.p.hand.group.removeAll(this.noOreList);
                    AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], this.amount, false);
                    tickDuration();
                    return;
                }
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractDungeon.handCardSelectScreen.selectedCards.group.forEach(this.p.hand::moveToExhaustPile);
            CardCrawlGame.dungeon.checkForPactAchievement();
            addToBot(new SelectCardToHand(new GetPool().returnRandomCardInCombat(3)));

            this.noOreList.forEach(this.p.hand::addToTop);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            numExhausted=AbstractDungeon.handCardSelectScreen.selectedCards.size();
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

}
