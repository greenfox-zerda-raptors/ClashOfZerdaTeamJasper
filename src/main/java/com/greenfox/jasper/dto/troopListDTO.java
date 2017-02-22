package com.greenfox.jasper.dto;

import java.util.ArrayList;
import java.util.List;

public class troopListDTO {

    private List<TroopDto> troops = new ArrayList<>();

    public troopListDTO() {
    }

    public troopListDTO(List<TroopDto> troops) {
        this.troops = troops;
    }

    public List<TroopDto> getTroops() {
        return troops;
    }

    public void setTroops(List<TroopDto> troops) {
        this.troops = troops;
    }
}
