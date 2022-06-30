package ApiMod.cards.basic;

import ApiMod.helpers.AssetId;
import ApiMod.helpers.AssetPath;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike extends CustomCard {
    //获取类名作为id,前方添加mod前缀
    public static final String ID = AssetId.makePath(Strike.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);//id获取本地化内容
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = AssetPath.makePath("img/cards/Strike.png");
    private static final int COST = 1;//费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;//描述
    private static final CardType TYPE = CardType.ATTACK;//类型
    private static final CardColor COLOR = CardColor.valueOf("API_PINK");//颜色
    private static final CardRarity RARITY = CardRarity.BASIC;//稀有度
    private static final CardTarget TARGET = CardTarget.ENEMY;//目标

    //装载信息
    public Strike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.tags.add(CardTags.STARTER_STRIKE);//基础打击标签
        this.tags.add(CardTags.STRIKE);//打击标签
    }

    //升级效果
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    //使用效果
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }
}
