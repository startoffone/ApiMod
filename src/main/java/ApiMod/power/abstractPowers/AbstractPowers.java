package ApiMod.power.abstractPowers;

import ApiMod.core.ApiMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractPowers extends AbstractPower {
    public AbstractPowers() {
    }

    public void  useTmpArt() {
        String path48 = ApiMod.assetPath("img/powers/test_power48.png");
        String path128 = ApiMod.assetPath("img/powers/test_power128.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
    }
}
