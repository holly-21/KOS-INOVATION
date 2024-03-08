package backend.model.dto;

import java.util.Date;

public class ReceiptDto {
    private int receiptId;
    private int userNum;
    private int stationId;
    private int chargeCost;
    private String chargeDate;
    private String stationName;
    public ReceiptDto(){};

    public ReceiptDto(int receiptId, int userNum,int stationId, int chargeCost, String chargeDate , String stationName) {
        this(userNum,stationId);
        this.receiptId = receiptId;
        this.chargeCost = chargeCost;
        this.chargeDate = chargeDate;
        this.stationName = stationName;
    }

    public ReceiptDto(int userNum,int stationId) {
        this.userNum = userNum;
        this.stationId = stationId;
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

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "ReceiptDto{" +
                "receiptId=" + receiptId +
                ", userNum=" + userNum +
                ", stationId=" + stationId +
                ", chargeCost=" + chargeCost +
                ", chargeDate='" + chargeDate + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
