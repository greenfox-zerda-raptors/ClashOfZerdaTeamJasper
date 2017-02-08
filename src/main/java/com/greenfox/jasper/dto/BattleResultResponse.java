package com.greenfox.jasper.dto;

/**
 * Created by almasics on 2017.02.08..
 */
public class BattleResultResponse {

    public BattleResultDto getBattleResultDto() {
        return battleResultDto;
    }

    public void setBattleResultDto(BattleResultDto battleResultDto) {
        this.battleResultDto = battleResultDto;
    }

    private BattleResultDto battleResultDto;

    public BattleResultResponse(BattleResultDto battleResultDto) {
        this.battleResultDto = battleResultDto;
    }
}
