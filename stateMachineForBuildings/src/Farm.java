/**
 * Created by Zoltán on 2017.01.18..
 */
public class Farm extends Building {
    protected int foodPerMinute = 10;

    public Farm(){

    }
    public Farm(long constructionTime, int buildingLevel, BuildingState buildingState){
        super(constructionTime, buildingLevel, buildingState);
    }

    public int getFoodPerMinute() {
        return foodPerMinute;
    }

    public void setFoodPerMinute(int foodPerMinute) {
        this.foodPerMinute = foodPerMinute;
    }
}
