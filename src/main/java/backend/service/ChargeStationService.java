package backend.service;

import backend.exception.SearchWrongException; // SearchWrongException 임포트해서 사용할 것임
import backend.model.dao.ChargeStationDao; // ChargeStationDao 임포트해서 사용할 것임
import backend.model.dao.ChargeStationDaoImpl; // ChargeStationDaoImpl 임포트해서 사용할 것임
import backend.model.dto.ChargeStationDto; // ChargeStationDto 임포트해서 사용할 것임

import java.sql.SQLException;
import java.util.List;

public class ChargeStationService { // ChargeStationService 클래스 정의
    // 위치 검색
    static ChargeStationDao chargeStationDao = new ChargeStationDaoImpl(); // ChargeStationDaooh 타입의 chargeStationDaooh
    /**
     * 충전소 위치 조회
     */

    public List<ChargeStationDto> searchStationService(String location) throws SQLException, SearchWrongException {
        List<ChargeStationDto> list = chargeStationDao.searchStation(location);

        // 만약 리스트 사이즈가 0이라면 예외 처리
        if(list.size() == 0){
            throw new SearchWrongException("결과가 없습니다.");
        }
        return list;
    }

    /**
     * 충전소 이름 조회
     */
    public List<ChargeStationDto>searchByStationName(String stationName) throws SQLException, SearchWrongException{
        List<ChargeStationDto> list = chargeStationDao.searchStation(stationName);

        if(list.size() == 0){
            throw new SearchWrongException("정보를 다시 확인해주세요.");
        }
        return list;
    }
}
