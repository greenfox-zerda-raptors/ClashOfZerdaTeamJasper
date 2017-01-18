/**
 * Created by Zoltán on 2017.01.18..
 */
public class Mine extends Building {
    protected int goldPerMinute = 10 * buildingLevel;

    public Mine() {
        super();
        this.constructionTime = 15000;
        this.baseCost = 40;
        this.buildingUpgradeCost = 40;
        this.buildingUpgradeTime = 10000;

    }

    public int getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(int goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }
}
