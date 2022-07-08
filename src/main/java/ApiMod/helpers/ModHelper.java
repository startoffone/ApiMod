package ApiMod.helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public interface ModHelper {
    //填写地址
    static String makePath(String path) {
        return "ApiMod/" + path;
    }
    //填写id
    static String makeId(String id) {
        return "ApiMod:" + id;
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
