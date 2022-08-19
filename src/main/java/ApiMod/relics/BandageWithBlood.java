package ApiMod.relics;

import ApiMod.core.ApiMod;
import ApiMod.relics.abstractRelics.AbstractRelics;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BandageWithBlood extends AbstractRelics {
    public static final String ID = ApiMod.makeID("BandageWithBlood");
    private boolean maxHealth;
    private final int strength = 2;
    private AbstractCreature p;

    public BandageWithBlood() {
        super(ID, true, RelicTier.STARTER, AbstractRelic.LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        p = AbstractDungeon.player;
        if (p.currentHealth == p.maxHealth) {
            applyToPlayer(new StrengthPower(p, this.strength));
            this.maxHealth = true;
        }
    }


    @Override
    public void wasHPLost(int damageAmount) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT
                && damageAmount > 0) {
            applyToPlayer(new StrengthPower(p, -this.strength));
            this.maxHealth = false;
        }
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        //非人物创建时
        if (p!=null) {
            //在战斗中且回满血
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                    p.currentHealth+healAmount >= p.maxHealth && !this.maxHealth) {
                applyToPlayer(new StrengthPower(p, this.strength));
                this.maxHealth = true;
            }

        }
        return healAmount;
    }

    @Override
    public void onTrigger() {
        addToTop(new RelicAboveCreatureAction(p, this));
        if (p.currentHealth > 0) {
            p.heal(2);
        }
    }
}
