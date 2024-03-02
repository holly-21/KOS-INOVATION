package backend.model.dto;

public class ChargerDto {
    private int chargerId;
    private int stationId;
    private int cost;
    private int status;
    private int speed;

    ChargerDto(){};

    public ChargerDto(int chargerId, int stationId, int cost, int status, int speed) {
        this.chargerId = chargerId;
        this.stationId = stationId;
        this.cost = cost;
        this.status = status;
        this.speed = speed;
    }

    public int getChargerId() {
        return chargerId;
    }

    public void setChargerId(int chargerId) {
        this.chargerId = chargerId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "ChargerDto{" +
                "chargerId=" + chargerId +
                ", stationId=" + stationId +
                ", cost=" + cost +
                ", status=" + status +
                ", speed=" + speed +
                '}';
    }
}
