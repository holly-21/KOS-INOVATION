package backend.dto;

import java.util.Date;

public class ReceiptDto {
    private int used ;
    private int userId;
    private int stationId;
    private int chargeCost;
    private Date chargeDate;

    public ReceiptDto(){};

    public ReceiptDto(int used, int userId, int stationId, int chargeCost, Date chargeDate) {
        this.used = used;
        this.userId = userId;
        this.stationId = stationId;
        this.chargeCost = chargeCost;
        this.chargeDate = chargeDate;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getChargeCost() {
        return chargeCost;
    }

    public void setChargeCost(int chargeCost) {
        this.chargeCost = chargeCost;
    }

    public Date getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
    }

    @Override
    public String toString() {
        return "ReceiptDto{" +
                "used=" + used +
                ", userId=" + userId +
                ", stationId=" + stationId +
                ", chargeCost=" + chargeCost +
                ", chargeDate=" + chargeDate +
                '}';
    }
}
