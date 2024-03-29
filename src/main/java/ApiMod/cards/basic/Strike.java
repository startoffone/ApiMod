package ApiMod.cards.basic;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL;


public class Strike extends AbstractCards {
    //获取类名作为id,前方添加mod前缀
    public static final String ID = ApiMod.makeID("Strike");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);//id获取本地化内容

    //装载信息
    public Strike() {
        super(ID, true, CARD_STRINGS, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setupDamage(6);
        this.tags.add(CardTags.STARTER_STRIKE);//基础打击标签
        this.tags.add(CardTags.STRIKE);//打击标签
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m,SLASH_DIAGONAL);
    }
    @Override
    public void limitedUpgrade() {
        this.upgradeDamage(3);
    }
}

