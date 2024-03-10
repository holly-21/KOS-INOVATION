package backend.model.dao;

import backend.model.dto.ChargeStationDto;

import java.sql.SQLException;
import java.util.List;

// ChargeStationDao 인터페이스 정의
/* 인터페이스를 사용하는 이유: 표준화! (틀이라고 생각) */
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


    List<ChargeStationDto> selectByStationName(String stationName) throws SQLException;


    List<ChargeStationDto> searchByOraganizationName(String organizationName) throws SQLException;
}
