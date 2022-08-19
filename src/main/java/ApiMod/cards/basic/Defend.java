package ApiMod.cards.basic;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends AbstractCards {
    //获取类名作为id,前方添加mod前缀
    public static final String ID = ApiMod.makeID("Defend");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);//id获取本地化内容

    public Defend() {
        super(ID, true, CARD_STRINGS, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.setupBlock(5);
        this.tags.add(CardTags.STARTER_DEFEND);//基础防御标签
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }

    @Override
    public void limitedUpgrade() {
        this.upgradeBlock(3);
    }
}
