package ApiMod.relics.shared;

import ApiMod.core.ApiMod;
import ApiMod.relics.abstractRelics.AbstractRelics;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class Boundaries extends AbstractRelics implements ClickableRelic {
    public static final String ID = ApiMod.makeID("Boundaries");

    public Boundaries() {
        super(ID, true, RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public void onEquip() {
        this.counter = 1;
    }

    @Override
    public void atBattleStart() {
        this.counter = 1;
    }

    @Override
    public void onRightClick() {
        if (this.counter == 1) {
            this.counter--;
            AbstractDungeon.player.decreaseMaxHealth(3);
            applyToPlayer(new IntangiblePlayerPower(AbstractDungeon.player, 1));
        }
    }
}
