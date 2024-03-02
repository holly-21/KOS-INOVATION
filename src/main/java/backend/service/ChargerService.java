package backend.service;

import backend.model.dao.ChargerDao;
import backend.model.dao.ChargerDaoImpl;
import backend.model.dto.ChargerDto;

import java.sql.SQLException;
import java.util.List;

public class ChargerService {
    ChargerDao chargerDao = new ChargerDaoImpl();

    //킬로와트 당 비용 조회
    public List<ChargerDto> preCalculateCost(String location, String speed, int chargeAmount) throws SQLException {
        List<ChargerDto> list= (List<ChargerDto>) chargerDao.preCalculateCost(location, speed, chargeAmount);
		if(list.size()==0)throw new SQLException("현재 상품이 없습니다."); //예외 재설정 필요
		return list;
	}

}
