package ApiMod.helpers;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.cards.ore.*;
import ApiMod.patches.Enums;
import ApiMod.power.PickaxePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class GetPool {

    public static ArrayList<AbstractOre> GetOrePool() {
        ArrayList<AbstractOre> orePool = new ArrayList<>();

        orePool.add(new Coal());
        orePool.add(new Diamond());
        orePool.add(new Emerald());
        orePool.add(new Fluorite());
        orePool.add(new Gold());
        orePool.add(new Iron());
        orePool.add(new Magma());
        orePool.add(new Obsidian());
        orePool.add(new Quartz());
        orePool.add(new RedStone());
        orePool.add(new Sand());
        orePool.add(new SilverFish());
        orePool.add(new Stone());

        return orePool;
    }

    //战斗中根据tag返回发现卡牌组
    public ArrayList<AbstractCard> returnTrulyRandomCardInCombat(AbstractCard.CardTags tag, int amount) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        ArrayList<AbstractCard> returnCards = new ArrayList<>();

        AbstractDungeon.srcCommonCardPool.group.stream().
                filter(card -> card.hasTag(tag) && !card.hasTag(AbstractCard.CardTags.HEALING)).forEach(list::add);
        AbstractDungeon.srcUncommonCardPool.group.stream().
                filter(card -> card.hasTag(tag) && !card.hasTag(AbstractCard.CardTags.HEALING)).forEach(list::add);
        AbstractDungeon.srcRareCardPool.group.stream().
                filter(card -> card.hasTag(tag) && !card.hasTag(AbstractCard.CardTags.HEALING)).forEach(list::add);

        for (int i = 0; i < amount; i++) {
            int temp = AbstractDungeon.cardRng.random(list.size() - 1);
            returnCards.add(list.get(temp));
            list.remove(temp);
        }
        return returnCards;
    }

    //返回当前镐子等级的卡池
    public ArrayList<AbstractCard> pickaxePool() {
        PickaxePower p = (PickaxePower) AbstractDungeon.player.getPower(PickaxePower.PowerID);
        ArrayList<AbstractCard> list = new ArrayList<>();
        if (p != null) {
            return p.cardsList;
        } else {
            GetPool.GetOrePool().stream().filter(c -> c.hasTag(Enums.Ore_Stone)).forEach(list::add);
            return list;
        }
    }


    public ArrayList<AbstractCard> returnRandomOresInCombat(int amount) {
        ArrayList<AbstractCard> list = pickaxePool();
        ArrayList<AbstractCard> returnCards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int temp = AbstractDungeon.cardRng.random(list.size() - 1);
            returnCards.add(list.get(temp));
            list.remove(temp);
        }
        return returnCards;
    }
}