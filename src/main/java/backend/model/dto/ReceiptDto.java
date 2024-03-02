package backend.model.dto;

import java.util.Date;

public class ReceiptDto {
    private int receiptId;
    private int userNum;
    private int stationId;
    private int chargeCost;
    private Date chargeDate;

    public ReceiptDto(){};

    public ReceiptDto(int receiptId, int userNum,int stationId, int chargeCost, Date chargeDate) {
        this.receiptId = receiptId;
        this.userNum = userNum;
        this.stationId = stationId;
        this.chargeCost = chargeCost;
        this.chargeDate = chargeDate;
    }

    @Override
    public String toString() {
        return "ReceiptDto{" +
                "receiptId=" + receiptId +
                ", userNum=" + userNum +
                ", stationId=" + stationId +
                ", chargeCost=" + chargeCost +
                ", chargeDate=" + chargeDate +
                '}';
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
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
}
