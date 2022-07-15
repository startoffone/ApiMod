package ApiMod.power;

import ApiMod.core.ApiMod;
import ApiMod.helpers.PowerHelper;
import ApiMod.patches.Enums;
import ApiMod.power.abstractPower.Weapon;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;

public class PickaxePower extends Weapon {
    public static final String PowerID = ApiMod.makeID("PickaxePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ArrayList<AbstractCard> cardsList;
    private int PickaxeLevel;

    public PickaxePower(AbstractCreature Owner, int PickaxeLevel, ArrayList<AbstractCard> cardsList) {
        this.ID = PowerID;
        this.name =DESCRIPTIONS[PickaxeLevel];
        this.owner = Owner;
        this.cardsList = cardsList;
        this.PickaxeLevel = PickaxeLevel;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        //将武器等级拼接进描述
        this.description = String.format(DESCRIPTIONS[PickaxeLevel+3], this.PickaxeLevel + 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(Enums.Weapon) && this.name != null) {
            String weapon = PowerHelper.CheckWeapon(card);
            if (weapon != null) {
                if (weapon.equals("ApiMod:PickaxePower")) {
                    addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                    addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            "ApiMod:Dig", this.PickaxeLevel + 1));
                }
            }
        }
    }
}
