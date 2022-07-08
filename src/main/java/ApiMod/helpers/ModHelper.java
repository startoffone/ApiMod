package ApiMod.helpers;

import ApiMod.power.Weapon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ModHelper {
    Logger logger = LogManager.getLogger(ModHelper.class.getTypeName());
    //填写地址
    static String makePath(String path) {
        return "ApiMod/" + path;
    }
    //填写id
    static String makeId(String id) {
        return "ApiMod:" + id;
    }
    static int checkWeapon(String name) {
        switch (name) {
            case "石镐":
                return 1;
            case "铁镐":
                return 2;
            case "钻石镐":
                return 3;
            default:
                logger.info("[ERROR]WeaponType: " + name + " not found");
                return 0;
        }
    }
    //填写匿名action
    static void addToBottom(Lambda func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }
    interface Lambda extends Runnable {}
}
