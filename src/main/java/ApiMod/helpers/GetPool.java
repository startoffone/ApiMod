package ApiMod.helpers;

import ApiMod.cards.abstractCards.AbstractOre;
import ApiMod.cards.ore.*;

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
}