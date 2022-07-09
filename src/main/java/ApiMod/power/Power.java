package ApiMod.power;

import ApiMod.helpers.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class Power extends AbstractPower {
    public Power() {
        this.amount = -1;
        Initialize();
        //首次添加能力更新描述
        this.updateDescription();
    }

    public Power( int Amount) {
        this.amount = Amount;
        Initialize();
    }

    protected void Initialize() {
        //添加默认能力图标
        String path48 = ModHelper.makePath("img/powers/test_power48.png");
        String path128 = ModHelper.makePath("img/powers/test_power128.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
    }
}
