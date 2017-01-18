/**
 * Created by almasics on 2017.01.18..
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Building mine = new Mine();
        mine.setEndUpgradeTime(System.currentTimeMillis() + 17000);
        System.out.println(mine.buildingToString());
        mine.setBuildingState(new LevelUp());
        mine.switchState();
        Thread.sleep(10000);
        mine.switchState();
        Thread.sleep(2000);
        mine.switchState();
        Thread.sleep(6000);
        mine.switchState();
        System.out.println(mine.buildingToString());
    }
}
