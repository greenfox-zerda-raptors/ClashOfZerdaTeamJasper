package com.greenfox.jasper.domain;

public class Battle {
    private Kingdom attackerTemp;
    private Kingdom defenderTemp;

    public Battle (Kingdom attackerReal, Kingdom defenderReal){
        this.attackerTemp = new Kingdom();
        this.attackerTemp.setTroops(attackerReal.getTroops());
        this.defenderTemp = new Kingdom();

        
    }
}
