package ApiMod.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public interface PowerHelper {
    static String CheckWeapon(AbstractCard card){
        if (AbstractDungeon.player.hasPower("ApiMod:PickaxePower")) {
            return "ApiMod:PickaxePower";
        }
        return null;
    }
}
