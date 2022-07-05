package ApiMod.cards.basic;

import ApiMod.cards.abstractCards.AbstractCard;
import ApiMod.helpers.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class Defend extends AbstractCard {
    //获取类名作为id,前方添加mod前缀
    public static final String ID = ModHelper.makePath(Defend.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);//id获取本地化内容

    public Defend() {
        super(ID, true, CARD_STRINGS, 1, SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.setupBlock(5);
        this.tags.add(CardTags.STARTER_DEFEND);//基础防御标签
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(3);
        this.upgradeDescription(CARD_STRINGS);
    }
}
