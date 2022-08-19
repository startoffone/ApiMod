package ApiMod.relics.abstractRelics;

import ApiMod.core.ApiMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;


public abstract class AbstractRelics extends CustomRelic {

    public AbstractRelics(String id, boolean useTmpArt, RelicTier RELIC_TIER, LandingSound LANDING_SOUND) {
        super(id, ImageMaster.loadImage(useTmpArt ? GetTmpImgPath() : GetImgPath(id)), RELIC_TIER, LANDING_SOUND);
    }

    protected static String GetTmpImgPath(){
        return ApiMod.assetPath("img/relics/test.png");
    }
    protected static String GetImgPath(String id){
        return String.format(ApiMod.assetPath("img/cards/%s.png"), id.replace(ApiMod.makeID(""), ""));
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    //给予玩家能力
    public void applyToPlayer(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }
}
