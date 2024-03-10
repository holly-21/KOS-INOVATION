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
    List<ChargeStationDto> searchStation(String location) throws SQLException;

    /**
     * 충전소 이름으로 찾기
     * @return stationId
     * (충전소 이름)
     */
    int searchByStationName(String stationName) throws SQLException;

    /**
     * 충전소 이름으로 충전소 찾기
     * @param stationName
     * @return 충전소 리스트
     * @throws SQLException
     */
    List<ChargeStationDto> selectByStationName(String stationName) throws SQLException;

    /**
     * 운영업체 이름으로 충전소 찾기
     * @param organizationName
     * @return 충전소 리스트
     * @throws SQLException
     */
    List<ChargeStationDto> searchByOraganizationName(String organizationName) throws SQLException;
}
