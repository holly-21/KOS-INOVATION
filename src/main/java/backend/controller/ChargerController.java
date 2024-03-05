package backend.controller;

import backend.model.dao.ChargerDao;
import backend.model.dto.ChargerDto;
import backend.service.ChargerService;
import front.FailView;
import front.SuccessView;

import java.sql.SQLException;

public class ChargerController {
    static ChargerService chargerService = new ChargerService();

    public static void preCalcCost(String stationName, String speed, int chargeAmount){
        try {
            int price = chargerService.preCalculateCost(stationName,speed,chargeAmount);
            SuccessView.messagePrint("[ "+stationName+" 충전소 ]에서 "+speed+"으로 충전할 경우, 예상 비용은 [ "+ price+"원 ]입니다.");
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }
}
