package ApiMod.cards.abstractCards;

import com.megacrit.cardcrawl.localization.CardStrings;

import static ApiMod.patches.Enums.Weapon;

public abstract class AbstractWeapon extends AbstractCards {
    public AbstractWeapon(String ID, boolean useTmpArt, CardStrings strings, int COST, CardRarity RARITY) {
        super(ID, useTmpArt, strings, COST, CardType.POWER, RARITY, CardTarget.SELF);
        this.tags.add(Weapon);
    }
}
