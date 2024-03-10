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
    // 문자열 형식의 String location을 받는 searchStation 메서드 정의(Dto 결과를 list 형식으로 출력)

    /**
     * 충전소 정렬
     * @return 충전소 리스트
     * (정렬 기준, 충전소 리스트)
     */
    List<ChargeStationDto> sortStationByStandard(int standard, List<ChargeStationDto> station);
    // 인트 형시의 standard와 list 형식으로 받아온 ChargeStationDto 안의 station을 받는 sortStationByStandard 메서드 정의

    /**
     * 충전소 이름으로 찾기
     * @return stationId
     * (충전소 이름)
     */
    int searchByStationName(String stationName) throws SQLException;
    // String 타입의 stationName을 받는 int 타입의 searchByStationName 정의
    // 여기서 질문했던 내용: 왜 searchByName을 int 타입으로 받는가?


    List<ChargeStationDto> selectByStationName(String stationName) throws SQLException;


}
