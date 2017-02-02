package com.greenfox.jasper.DTO;

import java.util.ArrayList;
import java.util.List;

public class TroopResponse {

    private List<TroopDto> troops = new ArrayList<>();

    public TroopResponse() {
    }

    public TroopResponse(List<TroopDto> troops) {
        this.troops = troops;
    }

    public List<TroopDto> getTroops() {
        return troops;
    }

    public void setTroops(List<TroopDto> troops) {
        this.troops = troops;
    }
}
