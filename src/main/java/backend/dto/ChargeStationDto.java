package backend.dto;

public class ChargeStationDto {
private int stationId;
private String location;
private String phone;
private String stationName;

public  ChargeStationDto (){};

    public ChargeStationDto(int stationId, String location, String phone, String stationName) {
        this.stationId = stationId;
        this.location = location;
        this.phone = phone;
        this.stationName = stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "ChargeStationDto{" +
                "stationId=" + stationId +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
