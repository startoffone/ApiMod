package ApiMod.cards.uncommon;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import ApiMod.helpers.CardHelper;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class Tunnelled extends AbstractCards {
    public static final String ID = ApiMod.makeID("Tunnelled");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Tunnelled() {
        super(ID, true, CARD_STRINGS, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust=true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot( new ExhaustAction(false, upgraded, true));
        CardHelper.addToBottom(()-> applyToPlayer(new PlatedArmorPower(p, ExhaustAction.numExhausted)));
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeDescription(CARD_STRINGS);
    }
}
