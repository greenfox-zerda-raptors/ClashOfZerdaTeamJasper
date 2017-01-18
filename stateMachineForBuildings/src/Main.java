/**
 * Created by almasics on 2017.01.18..
 */
public class Main {
    public static void main(String[] args) {
        Building barracks = new Building();
        barracks.switchState();
        barracks.switchState();
        barracks.setBuildingState(new LevelUp());
        barracks.switchState();
        barracks.switchState();
        barracks.switchState();


    }
}
