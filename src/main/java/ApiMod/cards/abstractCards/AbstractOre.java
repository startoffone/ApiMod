package ApiMod.cards.abstractCards;

import basemod.abstracts.CustomCard;

public abstract class AbstractOre extends CustomCard {
    public AbstractOre(String id, String name, String img, int cost, String rawDescription, CardType type, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, CardColor.COLORLESS, CardRarity.SPECIAL, target);
    }
}
