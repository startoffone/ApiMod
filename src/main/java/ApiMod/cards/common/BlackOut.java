package ApiMod.cards.common;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlackOut extends AbstractCards {
    public static final String ID = ApiMod.makeID("BlackOut");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private int str = 0;

    public BlackOut() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.setupMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        getStr();
        this.baseBlock=this.str*this.magicNumber;
        this.applyPowersToBlock();
        gainBlock();
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeMagicNumber(2);

    }
    //力量更新时，描述的防御数改变
    @Override
    public void applyPowers() {
        getStr();
        this.baseBlock=this.str*this.magicNumber;
        super.applyPowers();
        this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
    //进入弃牌堆，不再显示获得防御数
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }
    //影响防御数的能力生效（敏捷等）
    @Override
    protected void applyPowersToBlock() {
        super.applyPowersToBlock();
        this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
    private void getStr() {
        if (AbstractDungeon.player.hasPower("Strength")) {
            this.str = AbstractDungeon.player.getPower("Strength").amount;
        }
    }
}
