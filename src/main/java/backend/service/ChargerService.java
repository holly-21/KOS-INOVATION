package backend.service;

import backend.exception.IncorrectInputException;
import backend.exception.SearchWrongException;
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
    public int preCalculateCost(String stationName, String speed, int chargeAmount) throws SQLException, SearchWrongException,IncorrectInputException {

        //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
        int stationId = chargeStationDao.searchByStationName(stationName);
        int price=0;
        if(speed.equals("급속") || speed.equals("faster")) speed="faster";
        else if (speed.equals("완속") || speed.equals("lower")) speed="lower";
        else throw new IncorrectInputException("급속 or 완속 또는, faster or lower 중에 입력하세요");

        ChargerDto chargerDto = chargerDao.preCalculateCost(stationId, speed);
		if(chargerDto==null) throw new SearchWrongException("해당 충전소에 "+speed+" 기기가 없습니다.");
        price = chargerDto.getKwPrice()*chargeAmount;
		return price;
	}

}
