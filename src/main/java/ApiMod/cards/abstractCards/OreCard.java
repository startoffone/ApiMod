package ApiMod.cards.abstractCards;

import ApiMod.cards.special.ore.Gold;
import ApiMod.cards.special.ore.SilverFish;
import ApiMod.cards.special.ore.Stone;
import ApiMod.core.ApiMod;
import ApiMod.patches.Enums;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OreCard extends AbstractCards {
    public static final String ID = ApiMod.makeID("OreCard");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ApiMod:AbstractOre");
    public List<AbstractOre> Ores = new ArrayList<>();

    public OreCard(List<AbstractOre> Ores) {
        super(ID, true, CARD_STRINGS, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.Ores.addAll(Ores);
        this.upgraded = true;
        this.tags.add(Enums.Ore);
        oreCardInit();
    }


    @Override
    public void limitedUpgrade() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Ores.forEach(ore -> ore.use(p, m));
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        for (AbstractOre ore : Ores) {
            ore.triggerOnEndOfTurnForPlayingCard();
        }
    }

    private void oreCardInit() {
        AbstractOre baseOre = Ores.get(0);
        Ores.remove(0);

        //去重并添加词条
        List<String> nameStr = new ArrayList<>();
        List<String> descStr = new ArrayList<>();
        descStr.add(uiStrings.TEXT[0]);
        descStr.add(baseOre.returnDescription());

        for (AbstractOre ore : Ores) {
            if (nameStr.isEmpty()) {
                nameStr.add(ore.returnExtraDescription()[0]);
            } else if (!nameStr.contains(ore.returnExtraDescription()[0])) {
                nameStr.add(ore.returnExtraDescription()[0]);
            }

            //添加描述
            descStr.add(" NL " + ore.returnDescription());
            if (ore.cardID.equals(Gold.ID)|ore.cardID.equals(Stone.ID)) {
                this.exhaust = true;
            }
            if (ore.cardID.equals(SilverFish.ID)) {
                this.isEthereal = true;
            }
        }

        nameStr.add(baseOre.name);
        this.name = StringUtils.join(nameStr, "");
        initializeTitle();

        if (this.exhaust) {
            descStr.add(uiStrings.TEXT[2]);
            if (this.isEthereal) {
                descStr.add(uiStrings.TEXT[4]);
            }
        } else if (this.isEthereal) {
            descStr.add(uiStrings.TEXT[3]);
        }
        this.rawDescription = StringUtils.join(descStr, "");
        initializeDescription();

        Ores.add(baseOre);
    }


}
