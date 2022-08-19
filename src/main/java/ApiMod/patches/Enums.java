package ApiMod.patches;

import basemod.helpers.RelicType;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class Enums {
    //定义人物类
    @SpireEnum
    public static AbstractPlayer.PlayerClass APi_CLASS;
    //定义角色颜色标签
    @SpireEnum(name = "API_PINK")
    public static AbstractCard.CardColor APi_CARD;
    //在图书馆定义角色颜色标签
    @SpireEnum(name = "API_PINK")
    public static CardLibrary.LibraryType APi_LIBRARY;

    //定义卡牌标签矿石
    @SpireEnum
    public static AbstractCard.CardTags Ore;
    @SpireEnum
    public static AbstractCard.CardTags Ore_Stone;
    @SpireEnum
    public static AbstractCard.CardTags Ore_Iron;
    @SpireEnum
    public static AbstractCard.CardTags Ore_Diamond;
    //定义卡牌标签武器
    @SpireEnum
    public static AbstractCard.CardTags Weapon;
}
