package ApiMod.cards.uncommon;

import ApiMod.action.common.ExhaustOre;
import ApiMod.cards.abstractCards.AbstractCard;
import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.cards.ore.Gold;
import ApiMod.core.ApiMod;
import ApiMod.helpers.CardHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GoldenTouch extends AbstractCard {
    public static final String ID = ApiMod.makeID("GoldenTouch");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public GoldenTouch() {
        super(ID, true, CARD_STRINGS, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.setupMagicNumber(3);
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardHelper.addToBottom(() -> {
            addToBot(new ExhaustOre(this.magicNumber, true));
            if (this.upgraded) {
                AbstractOre Gold = new Gold();
                Gold.upgrade();
                addToBot(new MakeTempCardInHandAction(Gold, ExhaustOre.numExhausted));
            } else {
                addToBot(new MakeTempCardInHandAction(new Gold(), ExhaustOre.numExhausted));
            }
        });

    }
}
