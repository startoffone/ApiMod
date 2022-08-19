package ApiMod.characters;

import ApiMod.cards.basic.Defend;
import ApiMod.cards.basic.Strike;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import ApiMod.relics.BandageWithBlood;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

import static ApiMod.core.ApiMod.API_COLOR;
import static ApiMod.patches.Enums.APi_CARD;

public class Api extends CustomPlayer {

    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            ApiMod.assetPath("img/UI/orb/EPanel/layer5.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer4.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer3.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer2.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer1.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer0.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer5d.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer4d.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer3d.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer2d.png"),
            ApiMod.assetPath("img/UI/orb/EPanel/layer1d.png")
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]
            {-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    // 人物的本地化文本
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.
            getCharacterString(ApiMod.makeID("Api"));

    public Api(String name) {
        super(name, Enums.APi_CLASS, ORB_TEXTURES,
                //能量球特效
                ApiMod.assetPath("img/UI/orb/vfx.png"),
                LAYER_SPEED, null, null);

        // 人物对话气泡的大小（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);

        //人物初始设置
        this.initializeClass(
                // 人物图片
                null,
                // 火堆的人物立绘（行动前）
                ApiMod.assetPath("img/char/shoulder2.png"),
                // 火堆的人物立绘（行动后）
                ApiMod.assetPath("img/char/shoulder1.png"),
                // 人物死亡图像
                ApiMod.assetPath("img/char/corpse.png"),
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );
        // 人物动画
        this.loadAnimation(ApiMod.assetPath("img/char/MarisaModelv3.atlas"),
                ApiMod.assetPath("img/char/MarisaModelv3.json"), 1.8F);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(1.0F);

    }

    // 初始卡组的ID，可直接写或引用变量
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            retVal.add(Strike.ID);
            retVal.add(Defend.ID);
        }
        return retVal;
    }

    // 初始遗物的ID
    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(BandageWithBlood.ID);
        return retVal;
    }

    // 角色信息
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], // 人物名字
                characterStrings.TEXT[0], // 人物介绍
                75, // 当前血量
                75, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this,
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false
        );
    }

    // 人物名字（出现在游戏左上角）
    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    // 你的卡牌颜色（这个枚举在最下方创建）
    @Override
    public AbstractCard.CardColor getCardColor() {
        return APi_CARD;
    }
    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return API_COLOR;
    }

    // 翻牌事件出现的你的职业牌
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    //卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return API_COLOR;
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    // 创建人物实例
    @Override
    public AbstractPlayer newInstance() {
        return new Api(this.name);
    }

    // 第三章面对心脏说的话
    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    // 打心脏的颜色，不是很明显
    @Override
    public Color getSlashAttackColor() {
        return API_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

}
