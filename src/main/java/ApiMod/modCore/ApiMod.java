package ApiMod.modCore;

import ApiMod.cards.basic.Defend;
import ApiMod.cards.basic.Strike;
import ApiMod.cards.ore.Coal;
import ApiMod.characters.Api;
import ApiMod.helpers.AssetPath;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

import static ApiMod.pathes.Enums.APi_CLASS;
import static ApiMod.pathes.Enums.APi_CARD;

@SpireInitializer
public class ApiMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber {
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = AssetPath.makePath("img/charSelect/testButton.png");
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = AssetPath.makePath("img/charSelect/a_pi.jpg");
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = AssetPath.makePath("img/512/bg_attack_MRS_s.png");
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = AssetPath.makePath("img/512/bg_power_MRS_s.png");
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = AssetPath.makePath("img/512/bg_skill_MRS_s.png");
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = AssetPath.makePath("img/512/cardOrb.png");
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = AssetPath.makePath("img/1024/bg_attack_MRS.png");
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = AssetPath.makePath("img/1024/bg_power_MRS.png");
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = AssetPath.makePath("img/1024/bg_skill_MRS.png");
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = AssetPath.makePath("img/1024/cardOrb.png");
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENERGY_ORB = AssetPath.makePath("img/UI/energyOrb.png");
    //人物颜色
    public static final Color API_COLOR = CardHelper.getColor(255, 189, 190);

    public ApiMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(APi_CARD, API_COLOR, API_COLOR, API_COLOR, API_COLOR, API_COLOR, API_COLOR, API_COLOR, BG_ATTACK_512,
                BG_SKILL_512, BG_POWER_512, ENERGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new ApiMod();
    }

    //卡牌注册
    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Coal());
    }

    //本地化注册
    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "ApiMod/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "ApiMod/localization/" + lang + "/characters.json");
    }

    //人物注册
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Api(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, APi_CLASS);
    }
}
