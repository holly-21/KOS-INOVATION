package backend.model.dto;

public class ChargeStationRateDto extends ChargeStationDto {
    private double averageRate;

    public ChargeStationRateDto(double averageRate) {
        this.averageRate = averageRate;
    }

    public ChargeStationRateDto(int stationId, String organization, String location, String phone, String stationName, double averageRate) {
        super(stationId, organization, location, phone, stationName);
        this.averageRate = averageRate;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    @Override
    public String toString() {
        return "ChargeStationRateDto{" +
                "averageRate=" + averageRate +
                '}';
    }
}
