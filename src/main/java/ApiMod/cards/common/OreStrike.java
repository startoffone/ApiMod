package ApiMod.cards.common;

import ApiMod.cards.abstractCards.AbstractCards;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY;

public class OreStrike extends AbstractCards {
    public static final String ID = ApiMod.makeID("OreStrike");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private int cardAmount = 0, oreAmount = 0;//cardAmount记录本场战斗出牌数，oreAmount记录打出的矿石牌数

    public OreStrike() {
        super(ID, true, CARD_STRINGS, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        setupMagicNumber(4);
    }

    @Override
    public void limitedUpgrade() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        getOreAmount(true);
        this.baseDamage = oreAmount * this.magicNumber;
        this.oreAmount=0;//重置记录数
        calculateCardDamage(m);
        damageToEnemy(m, BLUNT_HEAVY);
    }

    @Override
    public void applyPowers() {
        getOreAmount(false);
        this.baseDamage = oreAmount * this.magicNumber;
        super.applyPowers();
        this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();

    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        this.rawDescription = CARD_STRINGS.DESCRIPTION + CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        initializeDescription();
    }

    private void getOreAmount(boolean ues) {
        AbstractCard card;int i;
        ArrayList<AbstractCard> cardList = AbstractDungeon.actionManager.cardsPlayedThisCombat;

        for (i = cardAmount; i < cardList.size(); ++i) {
            card = cardList.get(i);
            if (card.hasTag(Enums.Ore)) {
                ++this.oreAmount;
            }
        }
        if (ues) {
            this.cardAmount += i;
        }
    }
}
