package ApiMod.cards.abstractCards;

import ApiMod.helpers.ModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static ApiMod.pathes.Enums.APi_CARD;

public abstract class AbstractCard extends CustomCard {
    // 默认使用，useTmpArt表示是否使用测试卡图，当你卡图不够用时可以使用
    public AbstractCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE,
                        CardRarity RARITY, CardTarget TARGET) {
        super(ID, strings.NAME, useTmpArt ? GetTmpImgPath(TYPE) : GetImgPath(TYPE, ID), COST, strings.DESCRIPTION, TYPE,
                APi_CARD, RARITY, TARGET);
    }

    //非角色卡使用
    public AbstractCard(String ID, boolean useTmpArt, CardStrings strings, int COST, CardType TYPE,
                        CardColor COLOR, CardRarity RARITY, CardTarget TARGET) {
        super(ID, strings.NAME, useTmpArt ? GetTmpImgPath(TYPE) : GetImgPath(TYPE, ID), COST, strings.DESCRIPTION, TYPE,
                COLOR, RARITY, TARGET);
    }

    // 如果按这个方法实现，在cards文件夹下分别放test_attack.png、test_power.png、test_status.png、test_curse.png、test_skill.png
    private static String GetTmpImgPath(CardType t) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
                type = "status";
                break;
            case CURSE:
                type = "curse";
                break;
            case SKILL:
                type = "skill";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }
        return String.format(ModHelper.makePath("img/cards/test_%s.png"), type);
    }

    //如果实现这个方法，只要将相应类型的卡牌丢进相应文件夹即可，如攻击牌卡图添加进img/cards/attack/下
    private static String GetImgPath(CardType t, String name) {
        String type;
        switch (t) {
            case ATTACK:
                type = "attack";
                break;
            case POWER:
                type = "power";
                break;
            case STATUS:
                type = "status";
                break;
            case CURSE:
                type = "curse";
                break;
            case SKILL:
                type = "skill";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + t);
        }
        return String.format(ModHelper.makePath("img/cards/%s/%s.png"), type, name.replace(ModHelper.makePath(""), ""));
    }

    //以下三个方法可以快速设置伤害格挡特殊值的基础数值
    protected void setupDamage(int amt) {
        this.baseDamage = amt;
        this.damage = amt;
    }

    protected void setupBlock(int amt) {
        this.baseBlock = amt;
        this.block = amt;
    }

    protected void setupMagicNumber(int amt) {
        this.baseMagicNumber = amt;
        this.magicNumber = amt;
    }

    // 将描述设置为升级描述并更新描述
    protected void upgradeDescription(CardStrings cardStrings) {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    // 简化伤害代码
    public void damageToEnemy(AbstractMonster m, AbstractGameAction.AttackEffect effect) {
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), effect));
    }

    // 简化aoe代码
    public void damageToAllEnemies(AbstractGameAction.AttackEffect effect) {
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage,
                this.damageTypeForTurn, effect));
    }

    // 获得等于这张牌格挡数值的格挡
    public void gainBlock() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    // 获得一定格挡
    public void gainBlock(int amt) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, amt));
    }

    // 抽牌
    public void drawCards(int amt) {
        this.addToBot(new DrawCardAction(amt));
    }

    //给予玩家自身能力
    public void applyToPlayer(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }
    //给予怪物能力
    public void applyToMonster(AbstractMonster m,AbstractPower power) {
        this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, power));
    }

    //重写upgrade方法
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            limitedUpgrade();
        }
    }

    // 升级效果
    public void limitedUpgrade() {

    }
}

