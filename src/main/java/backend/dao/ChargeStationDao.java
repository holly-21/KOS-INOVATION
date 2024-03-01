package backend.dao;

import backend.dto.ChargeStationDto;

import java.util.List;

public interface ChargeStationDao {
    /**
     * 충전소 위치 조회
     * @return 충전소 정보
     * (검색할 위치)
     */
    List<ChargeStationDto> searchStation(String location);

    /**
     * 충전소 정렬
     * @return 충전소 정보
     * (정렬 기준, 충전소 정보)
     */
    List<ChargeStationDto> sortStationByStandard(int standard, ChargeStationDto station);
}
