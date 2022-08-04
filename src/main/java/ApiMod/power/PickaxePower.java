package ApiMod.power;

import ApiMod.cards.ore.Obsidian;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import ApiMod.power.abstractPower.Weapon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;

public class PickaxePower extends Weapon {
    public static final String PowerID = ApiMod.makeID("PickaxePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PowerID);
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public ArrayList<AbstractCard> cardsList;
    private final int PickaxeLevel;

    public PickaxePower(AbstractCreature Owner, int PickaxeLevel, ArrayList<AbstractCard> cardsList) {
        this.ID = PowerID;
        this.name = DESCRIPTIONS[PickaxeLevel];
        this.owner = Owner;
        this.cardsList = cardsList;
        this.PickaxeLevel = PickaxeLevel;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        //将武器等级拼接进描述
        this.description = String.format(DESCRIPTIONS[PickaxeLevel + 3], this.PickaxeLevel + 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //使用武器牌且不是自身
        if (card.hasTag(Enums.Weapon) && this.ID != null) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            addToBot(new ReducePowerAction(this.owner, this.owner,
                    Dig.PowerID, this.PickaxeLevel + 1));
        }
    }

    @Override
    public void onRemove() {
        if (this.PickaxeLevel == 1) {
            addToBot(new DrawCardAction(1, new AbstractGameAction() {
                public void update() {
                    DrawCardAction.drawnCards.forEach(card -> card.setCostForTurn(0));
                }
            }));
        } else {
            addToBot(new DrawCardAction(2));
            if (this.PickaxeLevel == 2) {
                addToBot(new MakeTempCardInHandAction(new Obsidian()));
            }
        }
    }
}
