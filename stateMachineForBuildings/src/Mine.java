/**
 * Created by Zoltán on 2017.01.18..
 */
public class Mine extends Building{
    protected int goldPerMinute = 10;

    public Mine(){

    }
    public Mine(long constructionTime, int buildingLevel, BuildingState buildingState){
        super(constructionTime, buildingLevel, buildingState);
    }

    public int getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(int goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }
}
