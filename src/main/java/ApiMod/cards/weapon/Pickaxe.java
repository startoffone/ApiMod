package ApiMod.cards.weapon;

import ApiMod.cards.abstractCards.AbstractWeapon;
import ApiMod.cards.ore.Coal;
import ApiMod.core.ApiMod;
import ApiMod.helpers.GetPool;
import ApiMod.patches.Enums;
import ApiMod.power.Dig;
import ApiMod.power.PickaxePower;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Pickaxe extends AbstractWeapon {
    public static final String ID = ApiMod.makeID("Pickaxe");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> cardsList = new ArrayList<>();

    public Pickaxe() {
        super(ID, true, CARD_STRINGS, 1, CardRarity.RARE, CardTarget.SELF);
        this.setupMagicNumber(1);
        addListByTag(Enums.Ore_Stone);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new PickaxePower(p, this.timesUpgraded, cardsList)); //给予镐子
        applyToPlayer(new Dig(p, this.timesUpgraded + 1));
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
        //获取与更新牌名，描述
        this.name = CARD_STRINGS.EXTENDED_DESCRIPTION[this.timesUpgraded];
        initializeTitle();
        this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[this.timesUpgraded + 2];
        this.initializeDescription();

        //设置为已升级
        if (this.timesUpgraded == 0) {
            this.upgradeBaseCost(2);
            addListByTag(Enums.Ore_Iron);
        }
        if (this.timesUpgraded == 1) {
            this.upgraded = true;
            addListByTag(Enums.Ore_Diamond);
        }
        ++this.timesUpgraded;
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                if (this.cardsList.size() == 0) {
                    this.cardsToPreview = new Coal();
                } else {
                    this.cardsToPreview = this.cardsList.get(this.previewIndex);
                }
                if (this.previewIndex == this.cardsList.size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    private void addListByTag(CardTags tag) {
        for (AbstractCard c : GetPool.GetOrePool()) {
            if (c.hasTag(tag)) {
                cardsList.add(c);
            }
        }
    }
}