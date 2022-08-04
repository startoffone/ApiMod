package ApiMod.action;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.cards.abstractCards.OreCard;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComposeOreAction extends AbstractGameAction {
    private AbstractPlayer p;
    private List<AbstractCard> cardList;

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ApiMod.makeID("ComposeOreAction"));

    public ComposeOreAction(int Amount) {
        this.amount = Amount;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            cardList = this.p.hand.group.stream().filter(card -> !card.hasTag(Enums.Ore)).collect(Collectors.toList());
            int oreAmount = this.p.hand.group.size() - cardList.size();

            if (oreAmount == 0) {
                this.isDone = true;
                return;
            }

            if (oreAmount >= this.amount) {
                this.p.hand.group.removeAll(this.cardList);
                AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], this.amount, false);
                tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            this.cardList.forEach(this.p.hand::addToTop);
            makeOreCard();

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void makeOreCard() {
        List<AbstractOre> OreList = new ArrayList<>();
        AbstractDungeon.handCardSelectScreen.selectedCards.group.forEach(card -> addOreCardList(card,OreList));

        if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(new OreCard(OreList), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        } else {
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(new OreCard(OreList), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        }
    }

    private void addOreCardList(AbstractCard card,List<AbstractOre> oreList){
        OreCard oreCard;
        if (card instanceof AbstractOre) {
            oreList.add((AbstractOre) card);
        } else {
            oreCard = (OreCard) card;
            oreList.addAll(oreCard.Ores);
        }
    }
}
