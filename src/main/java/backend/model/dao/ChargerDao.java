package backend.model.dao;

import backend.model.dto.ChargerDto;

import java.sql.SQLException;

public interface ChargerDao {
    /**
     * 킬로와트 당 비용 조회
     * @return 충전기기 정보
     * (충전소 위치, 충전 속도, 충전량)
     */
    ChargerDto preCalculateCost(String location, String speed, int chargeAmount) throws SQLException;
}
