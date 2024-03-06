package backend.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface RecieptDao {
    /**
     * 결제 내역 조회
     * @return 결제 아이디
     * (사용자 아이디, 충전소 이름)
     */
    int SearchReceipt(int userNum, int stationId) throws SQLException;

    /**
     * 킬로와트 당 예상 비용 계산
     * @return 예상 비용
     * (충전기기 정보)
     */


    /**
     * 잔액과 사전계산으로 결제 금액 계산
     * @return 잔액
     * (사용자 아이디, 잔액, 예상 비용)
     */
    int payCost(String userId, int balance, int expectCost);


    /**
     * StationId 별로 결제금액이 높은순으로 정렬
     * @return Station Id List
     */
    List<String> selectReceiptOrderByCost();
}



