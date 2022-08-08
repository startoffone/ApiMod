package ApiMod.relics.abstractRelics;

import ApiMod.core.ApiMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;


public abstract class abstractRelics extends CustomRelic {

    public abstractRelics(String id, boolean useTmpArt, RelicTier RELIC_TIER, LandingSound LANDING_SOUND) {
        super(id, ImageMaster.loadImage(useTmpArt ? GetTmpImgPath() : GetImgPath(id)), RELIC_TIER, LANDING_SOUND);
    }

    protected static String GetTmpImgPath(){
        return ApiMod.assetPath("img/relics/test.png");
    }
    protected static String GetImgPath(String id){
        return String.format(ApiMod.assetPath("img/cards/%s.png"), id.replace(ApiMod.makeID(""), ""));
    }
}
