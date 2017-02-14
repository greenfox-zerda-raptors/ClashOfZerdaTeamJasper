package com.greenfox.jasper.dto;

import java.util.List;

public class KingdomListResponse {
    private List<KingdomDto> allKingdoms;

    public KingdomListResponse() {
    }

    public KingdomListResponse(List<KingdomDto> allKingdoms) {
        this.allKingdoms = allKingdoms;
    }

    public List<KingdomDto> getAllKingdoms() {
        return allKingdoms;
    }

    public void setAllKingdoms(List<KingdomDto> allKingdoms) {
        this.allKingdoms = allKingdoms;
    }
}
