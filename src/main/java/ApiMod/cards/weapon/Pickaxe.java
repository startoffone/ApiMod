package ApiMod.cards.weapon;

import ApiMod.action.DigOre;
import ApiMod.cards.abstractCards.AbstractWeapon;
import ApiMod.helpers.ModHelper;
import ApiMod.power.Dig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pickaxe extends AbstractWeapon {
    public static final String ID = ModHelper.makeId(Pickaxe.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Pickaxe() {
        super(ID, true, CARD_STRINGS, 1, CardRarity.RARE, CardTarget.SELF);
        this.setupMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new ApiMod.power.Pickaxe(p,this.name));//给予镐子
        applyToPlayer(new Dig(p,timesUpgraded+1));//给予镐子等级的挖掘层数
        addToBot(new DigOre(p,timesUpgraded+1));//给予镐子等级数量的矿石
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            limitedUpgrade();
        }
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(1);
        //获取与更新牌名
        this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[this.timesUpgraded];
        initializeTitle();
        this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[this.timesUpgraded + 2];
        this.initializeDescription();
        //设置为已升级
        if (this.timesUpgraded == 1) {
            this.upgraded = true;
        }
        ++this.timesUpgraded;
    }
}