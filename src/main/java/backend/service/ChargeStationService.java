package backend.service;

import backend.exception.SearchWrongException;
import backend.model.dao.ChargeStationDao;
import backend.model.dao.ChargeStationDaoImpl;
import backend.model.dto.ChargeStationDto;
import front.locFront;

import java.sql.SQLException;
import java.util.List;

public class ChargeStationService {

    // 위치 검색
    static ChargeStationDao chargeStationDao = new ChargeStationDaoImpl();

    /**
     * 충전소 위치 조회
     */
    public List<ChargeStationDto> searchStationService(String location) throws SQLException, SearchWrongException {
        List<ChargeStationDto> list = chargeStationDao.searchStation(location);

        if (list.isEmpty()) {
            throw new SearchWrongException("결과가 없습니다.");
        }
        return list;
    }

    /**
     * 충전소 이름 조회
     */
    public List<ChargeStationDto> searchByStationName(String stationName) throws SQLException, SearchWrongException {
        List<ChargeStationDto> list = chargeStationDao.selectByStationName(stationName);

        if (list.isEmpty()) {
            throw new SearchWrongException("일치하는 정보가 없습니다 \n 정보를 다시 확인해주세요.");
        }
        return list;
    }

    public List<ChargeStationDto> searchByOraganizationName(String organizationName) throws SQLException, SearchWrongException {
        List<ChargeStationDto> list = chargeStationDao.searchByOraganizationName(organizationName);

        if (list.isEmpty()) {
            throw new SearchWrongException("일치하는 정보가 없습니다 \n 정보를 다시 확인해주세요.");
        }
        return list;
    }
}
