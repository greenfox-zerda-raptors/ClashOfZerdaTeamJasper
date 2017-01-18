/**
 * Created by almasics on 2017.01.18..
 */

public class ProductionInProgress implements BuildingState {

    @Override
    public void switchState(Building building) {
        long currenttime = System.currentTimeMillis();
        System.out.println("Building production started");
        building.setBuildingState(new Idle());
    }

}
