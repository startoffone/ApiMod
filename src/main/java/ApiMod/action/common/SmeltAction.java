package ApiMod.action.common;

import ApiMod.core.ApiMod;
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

public class SmeltAction extends AbstractGameAction {
    private AbstractPlayer p;
    private List<AbstractCard> noOreList;
    private int oreAmount;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ApiMod.makeID("SmeltAction"));
    public static final String[] TEXT = uiStrings.TEXT;

    public SmeltAction(int amount) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (!hasOre()) {
                this.isDone = true;
                return;
            }
            if (oreAmount == this.amount) {
                this.p.hand.group.stream().filter(card -> card.hasTag(Enums.Ore)).forEach(this.p.hand::moveToExhaustPile);
                CardCrawlGame.dungeon.checkForPactAchievement();

                tickDuration();
                return;
            }
            if (noOreList.size() > this.amount) {
                this.p.hand.group.removeAll(this.noOreList);
                AbstractDungeon.handCardSelectScreen.open(String.format(TEXT[0], this.amount), this.amount, false);
                tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractDungeon.handCardSelectScreen.selectedCards.group.forEach(this.p.hand::moveToExhaustPile);
            CardCrawlGame.dungeon.checkForPactAchievement();

            this.noOreList.forEach(c->this.p.hand.addToTop(c));
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private boolean hasOre() {
        noOreList = this.p.hand.group.stream().filter(card -> !card.hasTag(Enums.Ore)).collect(Collectors.toList());
        oreAmount = this.p.hand.group.size() - noOreList.size();
        return this.p.hand.group.size() - noOreList.size() > 0;
    }
}
