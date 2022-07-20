package ApiMod.action.common;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

public class SelectCardToHand extends AbstractGameAction {
    private ArrayList<AbstractCard> cards;
    private boolean retrieveCard = false;
    private boolean isUpgrade;

    public SelectCardToHand(ArrayList<AbstractCard> cards) {
        this.cards = cards;
        this.isUpgrade = false;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public SelectCardToHand(ArrayList<AbstractCard> cards, boolean isUpgrade) {
        this.cards = cards;
        this.isUpgrade = isUpgrade;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
            return;
        }

        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(this.cards, CardRewardScreen.TEXT[1], false);

            tickDuration();
            return;
        }

        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                if (this.isUpgrade) {
                    if (!disCard.upgraded&&disCard.canUpgrade()) {
                        disCard.upgrade();
                    }
                }
                disCard.current_x = -1000.0F * Settings.scale;
                if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

}
