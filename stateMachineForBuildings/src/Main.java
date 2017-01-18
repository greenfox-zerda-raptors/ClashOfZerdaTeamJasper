/**
 * Created by almasics on 2017.01.18..
 */
public class Main {
    public static void main(String[] args) {
        Building mine = new Mine();
        System.out.println(mine.buildingToString());
        mine.setBuildingState(new LevelUp());
        mine.switchState();
        System.out.println(mine.buildingToString());
    }
}
