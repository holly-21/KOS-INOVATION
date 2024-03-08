package backend.model.dto;

public class ChargeStationCostSumDto extends ChargeStationDto{
    private int costSum;

    public ChargeStationCostSumDto(int costSum) {
        this.costSum = costSum;
    }

    public ChargeStationCostSumDto(int stationId, String organization, String location, String phone, String stationName, int costSum) {
        super(stationId, organization, location, phone, stationName);
        this.costSum = costSum;
    }

    public int getCostSum() {
        return costSum;
    }

    @Override
    public String toString() {
        return "ChargeStationCostSumDto{" +
                "costSum=" + costSum +
                '}';
    }
}
