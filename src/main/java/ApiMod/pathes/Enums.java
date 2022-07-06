package ApiMod.pathes;

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
    //定义卡牌矿石tag
    @SpireEnum
    public static AbstractCard.CardTags Ore;
}
