package ApiMod.power;

import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class Pickaxe extends Weapon {
    private static final String PowerID = ModHelper.makeId(Pickaxe.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Pickaxe(AbstractCreature Owner,String Name) {
        this.ID=PowerID;
        this.name=Name;
        this.owner=Owner;
    }

    @Override
    public void updateDescription() {
        //将武器等级拼接进描述
        int weaponType = checkWeapon(this.name);
        this.description = this.description + String.format(DESCRIPTIONS[weaponType], weaponType+1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AbstractCard.CardTags.valueOf("Weapon"))) {
            addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    "ApiMod:Dig", checkWeapon(this.name)));
            this.name= card.name;
        }
    }


    protected int checkWeapon(String name) {
        switch (name) {
            case "铁镐":
                return 1;
            case "钻石镐":
                return 2;
            default:
                return 0;
        }
    }
}
