package ApiMod.cards.rare;

import ApiMod.action.common.SelectCardToHand;
import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Rummage extends AbstractCards {
    public static final String ID = ApiMod.makeID("Rummage");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Rummage() {
        super(ID, true, CARD_STRINGS, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        if (!p.drawPile.isEmpty()) {
            cards.addAll(p.drawPile.group);
        }
        if (!p.discardPile.isEmpty()) {
            cards.addAll(p.discardPile.group);
        }
        if (!cards.isEmpty()) {
            Consumer<AbstractCard> callback = card -> {
                if (p.drawPile.contains(card)) {
                    p.drawPile.removeCard(card);
                } else {
                    p.discardPile.removeCard(card);
                }
            };
            addToBot(new SelectCardToHand(cards, 1, callback));
        }
    }

    @Override
    public void limitedUpgrade() {
        this.exhaust = false;
        this.upgradeDescription(CARD_STRINGS);
    }
}
