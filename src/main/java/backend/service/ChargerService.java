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
    public int preCalculateCost(String stationName, String speed, int chargeAmount) throws SQLException {

        //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
        int stationId = chargeStationDao.searchByStationName(stationName);
        int price=0;
        if(speed.equals("급속")) speed="faster";
        else if (speed.equals("완속")) speed="lower";

        ChargerDto chargerDto = chargerDao.preCalculateCost(stationId, speed);
		if(chargerDto==null)throw new SQLException("해당 충전소에서 검색할 수 없습니다."); //예외 재설정 필요
        price = chargerDto.getKwPrice()*chargeAmount;
		return price;
	}

}
