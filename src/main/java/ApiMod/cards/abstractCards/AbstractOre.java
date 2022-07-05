package ApiMod.cards.abstractCards;

import com.megacrit.cardcrawl.localization.CardStrings;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.SPECIAL;

public abstract class AbstractOre extends AbstractCard {

    public AbstractOre(String ID, boolean useTmpArt, CardStrings strings, CardType TYPE, CardTarget TARGET) {
        super(ID, useTmpArt, strings, 0, TYPE, CardColor.COLORLESS,SPECIAL, TARGET);
    }
}
