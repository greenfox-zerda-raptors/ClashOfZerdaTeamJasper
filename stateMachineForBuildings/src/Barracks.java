/**
 * Created by almasics on 2017.01.18..
 */
public class Barracks extends Building {

    private long unitBuildTime;

    public Barracks() {
        super();
        this.constructionTime = 30000;
        this.unitBuildTime = 10000;
        this.baseCost = 100;
    }

    public long getUnitBuildTime() {
        return unitBuildTime;
    }

    public void setUnitBuildTime(long unitBuildTime) {
        this.unitBuildTime = unitBuildTime;
    }
}
