package ApiMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ExchangeRealityAction extends AbstractGameAction {

    private final AbstractPlayer p = AbstractDungeon.player;

    @Override
    public void update() {


        if (!p.exhaustPile.isEmpty()) {
            if (!p.drawPile.isEmpty()) {
                ArrayList<AbstractCard> cards = new ArrayList<>(p.exhaustPile.group);
                moveToExhaust();
                for (AbstractCard card : cards){
                    card.unfadeOut();
                    card.untip();
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    p.drawPile.addToTop(card);
                    p.exhaustPile.removeCard(card);
                }
            } else moveToDraw();
        } else if (!p.drawPile.isEmpty()) moveToExhaust();

        this.isDone = true;
    }

    private void moveToExhaust() {
        ArrayList<AbstractCard> cards = new ArrayList<>(p.drawPile.group);
        for (AbstractCard card : cards) {
            p.drawPile.moveToExhaustPile(card);
        }
    }
    private void moveToDraw(){
        ArrayList<AbstractCard> cards = new ArrayList<>(p.exhaustPile.group);
        for (AbstractCard card : cards){
            card.unfadeOut();
            card.untip();
            card.unhover();
            card.lighten(true);
            card.setAngle(0.0F);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.current_x = CardGroup.DRAW_PILE_X;
            card.current_y = CardGroup.DRAW_PILE_Y;
            p.drawPile.addToTop(card);
        }
        p.exhaustPile.clear();
    }

}
