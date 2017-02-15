package com.greenfox.jasper.domain.TimedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public class TrainTroopEvent extends TimedEvent {
//    @OneToOne(mappedBy = "building", cascade = CascadeType.ALL)
    @Column(name = "kingdom_id")
    private long kingdomId;

    public TrainTroopEvent(){

    }

    public TrainTroopEvent(long executionTime, long kingdomId) {
        super(executionTime);
        this.kingdomId = kingdomId;
    }

    public long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(long kingdomId) {
        this.kingdomId = kingdomId;
    }
}
