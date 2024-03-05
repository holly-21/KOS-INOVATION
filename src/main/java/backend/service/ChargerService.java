package backend.service;

import backend.model.dao.ChargeStationDao;
import backend.model.dao.ChargeStationDaoImpl;
import backend.model.dao.ChargerDao;
import backend.model.dao.ChargerDaoImpl;
import backend.model.dto.ChargerDto;

import java.sql.SQLException;
import java.util.List;

public class ChargerService {
    ChargerDao chargerDao = new ChargerDaoImpl();
    ChargeStationDao chargeStationDao = new ChargeStationDaoImpl();

    //킬로와트 당 비용 조회
////    public List<ChargerDto> preCalculateCost(String location, String stationName String speed, int chargeAmount) throws SQLException {
////        //ChargeStationDaoImpl에서 충전소 위치로 충전소Id 찾기
////        List<ChargerDto> list = chargeStationDao.searchByStationName(location);
////        int stationId = chargeStationDao.searchByStationName(stationName) ;
////        List<ChargerDto> list = chargerDao.preCalculateCost(stationId, speed, chargeAmount);
////		if(list.size()==0)throw new SQLException("현재 상품이 없습니다."); //예외 재설정 필요
////		return list;
//	}

}
