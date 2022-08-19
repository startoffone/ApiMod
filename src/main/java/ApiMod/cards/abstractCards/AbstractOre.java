package ApiMod.cards.abstractCards;

import ApiMod.core.ApiMod;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import static ApiMod.patches.Enums.Ore;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.SPECIAL;

public abstract class AbstractOre extends AbstractCards {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ApiMod.makeID("AbstractOre"));
    protected String Description;
    protected final String[] ExtendDescription;

    public AbstractOre(String ID, boolean useTmpArt, CardStrings strings, CardType TYPE, CardTarget TARGET) {
        super(ID, useTmpArt, strings, 0, TYPE, CardColor.COLORLESS, SPECIAL, TARGET);
        this.exhaust = true;//消耗
        this.tags.add(Ore);//标签
        this.Description=strings.DESCRIPTION;
        this.ExtendDescription =strings.EXTENDED_DESCRIPTION;

        this.rawDescription = uiStrings.TEXT[0] + this.rawDescription + uiStrings.TEXT[2];
        initializeDescription();
    }

    public AbstractOre(String ID, boolean useTmpArt, CardStrings strings, CardType TYPE, CardTarget TARGET, boolean Ethereal) {
        super(ID, useTmpArt, strings, -2, TYPE, CardColor.COLORLESS, SPECIAL, TARGET);
        this.isEthereal = Ethereal;//虚无
        this.tags.add(Ore);//标签
        this.Description=strings.DESCRIPTION;
        this.ExtendDescription =strings.EXTENDED_DESCRIPTION;

        if (this.rawDescription.isEmpty()) {
            this.rawDescription=uiStrings.TEXT[1]+uiStrings.TEXT[3];
        } else {
            this.rawDescription = uiStrings.TEXT[0] + this.rawDescription;
        }
        initializeDescription();
    }

    @Override
    protected void upgradeDescription(CardStrings cardStrings) {
        this.Description=cardStrings.DESCRIPTION;
        this.rawDescription = uiStrings.TEXT[0] + cardStrings.UPGRADE_DESCRIPTION + uiStrings.TEXT[1];
        this.initializeDescription();
    }

    public  String[] returnExtraDescription(){
        return this.ExtendDescription;
    }

    public String returnDescription(){
        return this.Description.replaceAll("!D!", String.valueOf(this.damage))
                .replaceAll("!B!", String.valueOf(this.block))
                .replaceAll("!M!", String.valueOf(this.magicNumber));
    }
}
