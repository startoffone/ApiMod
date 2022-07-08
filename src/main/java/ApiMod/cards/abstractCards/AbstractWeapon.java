package ApiMod.cards.abstractCards;

import com.megacrit.cardcrawl.localization.CardStrings;

import static ApiMod.pathes.Enums.Weapon;

public abstract class AbstractWeapon extends AbstractCard{
    public AbstractWeapon(String ID, boolean useTmpArt, CardStrings strings, int COST, CardRarity RARITY, CardTarget TARGET) {
        super(ID, useTmpArt, strings, COST, CardType.POWER, RARITY, TARGET);
        this.tags.add(Weapon);
    }
}
