package ApiMod.cards.ore;

import ApiMod.action.SelectCardToHand;
import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.core.ApiMod;
import ApiMod.helpers.CardHelper;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Emerald extends AbstractOre {
    public static final String ID = ApiMod.makeID("Emerald");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Emerald() {
        super(ID, true, CARD_STRINGS, CardType.SKILL, CardTarget.NONE);
        this.tags.add(Enums.Ore_Iron);
    }

    @Override
    public void limitedUpgrade() {
        upgradeDescription(CARD_STRINGS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardToHand(CardHelper.returnTrulyRandomCardInCombat(Enums.Weapon,3),this.upgraded));
    }
}
