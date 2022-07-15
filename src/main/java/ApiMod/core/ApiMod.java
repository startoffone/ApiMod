package ApiMod.core;

import ApiMod.characters.Api;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;

import static ApiMod.patches.Enums.APi_CLASS;
import static ApiMod.patches.Enums.APi_CARD;

@SpireInitializer
public class ApiMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber {
    public static String MOD_ID = "ApiMod";
    public static final Color API_COLOR = CardHelper.getColor(255, 189, 190);

    public ApiMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(APi_CARD, API_COLOR, API_COLOR, API_COLOR, API_COLOR, API_COLOR, API_COLOR, API_COLOR,
                // 攻击牌的背景（小尺寸）
                assetPath("img/512/bg_attack_MRS_s.png"),
                // 技能牌的背景（小尺寸）
                assetPath("img/512/bg_skill_MRS_s.png"),
                // 能力牌的背景（小尺寸）
                assetPath("img/512/bg_power_MRS_s.png"),
                // 小尺寸的能量图标（战斗中，牌堆预览）
                assetPath("img/UI/energyOrb.png"),
                // 攻击牌的背景（大尺寸）
                assetPath("img/1024/bg_attack_MRS.png"),
                // 技能牌的背景（大尺寸）
                assetPath("img/1024/bg_skill_MRS.png"),
                // 能力的背景（大尺寸）
                assetPath("img/1024/bg_power_MRS.png"),
                // 在卡牌预览界面的能量图标
                assetPath("img/1024/cardOrb.png"),
                // 在卡牌和遗物描述中的能量图标
                assetPath("img/512/cardOrb.png"));
    }


    public static String makeID(String id) {
        /* 163 */
        return MOD_ID + ":" + id;
        /*     */
    }

    public static String assetPath(String path) {
        /* 167 */
        return MOD_ID + "/" + path;
        /*     */
    }


    public static void initialize() {
        /* 177 */
        new ApiMod();
        /*     */
    }


    //卡牌注册
    @Override
    public void receiveEditCards() {
        new AutoAdd("ApiMod")
                .packageFilter("ApiMod.cards")
                .setDefaultSeen(true)
                .cards();

    }

    //读取本地语言
    private Settings.GameLanguage languageRead() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            return Settings.language;
        }
        return Settings.GameLanguage.ENG;
    }

    //本地化注册
    @Override
    public void receiveEditStrings() {
        String lang = languageRead().toString();
        BaseMod.loadCustomStringsFile(CardStrings.class, assetPath("localization/" + lang + "/cards.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, assetPath("localization/" + lang + "/characters.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, assetPath("localization/" + lang + "/powers.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, assetPath("localization/" + lang + "/ui.json"));
    }

    //人物注册
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Api(CardCrawlGame.playerName),
                // 人物选择界面按钮的图片
                assetPath("img/charSelect/testButton.png"),
                // 人物选择界面的立绘
                assetPath("img/charSelect/a_pi.jpg"),
                APi_CLASS);
    }

    //添加关键字
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = languageRead().toString();
        String json = Gdx.files.internal(assetPath("localization/" + lang + "/keywords.json"))
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("api_mod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
