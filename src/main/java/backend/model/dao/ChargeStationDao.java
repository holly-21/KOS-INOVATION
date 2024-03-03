package backend.model.dao;

import backend.model.dto.ChargeStationDto;

import java.sql.SQLException;
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
     * @return 충전소 리스트
     * (정렬 기준, 충전소 리스트)
     */
    List<ChargeStationDto> sortStationByStandard(int standard, List<ChargeStationDto> station);

    /**
     * 충전소 이름으로 찾기
     * @return stationId
     * (충전소 이름)
     */
    int searchByStationName(String stationName) throws SQLException;
}
