/**
 * Created by Zoltán on 2017.01.18..
 */
public class Townhall extends Building {
    protected int goldPerMinute = 10;
    protected int foodPerMinute = 10;

    public Townhall(){

    }
    public Townhall(long constructionTime, int buildingLevel, BuildingState buildingState){
        super(constructionTime, buildingLevel, buildingState);
    }

    public int getGoldPerMinute() {
        return goldPerMinute;
    }

    public void setGoldPerMinute(int goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }

    public int getFoodPerMinute() {
        return foodPerMinute;
    }

    public void setFoodPerMinute(int foodPerMinute) {
        this.foodPerMinute = foodPerMinute;
    }
}
