package backend.model.dao;

import backend.model.dto.ChargeStationCostSumDto;
import backend.model.dto.ReceiptDto;

import java.sql.SQLException;
import java.util.List;


public interface RecieptDao {
    /**
     * 결제 내역이 존재하는지 조회
     * @return 결제 아이디
     * (사용자 아이디, 충전소 이름)
     */
    int isDuplicate(int userNum, int stationId) throws SQLException;

    /**
     * 결제내역ID로 충전소ID 찾기
     * @param receiptId
     * @return stationId
     * @throws SQLException
     */
    int SearchReceipt(int receiptId) throws SQLException;

    /**
     * 사용자아이디로 결제한 충전소 이름과 결제 내역 불러오기
     * @param userId
     * @return 결제 내역 정보
     */
    List<ReceiptDto> searchMyRecipt(String userId);


    /**
     * 잔액과 예상결제비용으로 결제 금액 계산
     * @param userNUm
     * @param balance
     * @param expectCost
     * @return
     * @throws SQLException
     */
    int payCost(int userNUm, int balance, int expectCost) throws SQLException;


    /**
     * StationId 별로 결제금액이 높은순으로 정렬
     * @return StationId List
     */
    List<ChargeStationCostSumDto> selectReceiptOrderByCost();
}



