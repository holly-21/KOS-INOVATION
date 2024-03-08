package backend.controller;

import backend.exception.IncorrectInputException;
import backend.exception.SearchWrongException;
import backend.model.session.SessionSet;
import backend.service.ChargerService;
import front.*;

import java.sql.SQLException;

public class ChargerController {
    static ChargerService chargerService = new ChargerService();
    static UserFront userFront= new UserFront();
    static NonUserFront nonUserFront = new NonUserFront();

    public static void preCalcCost(String stationName, String speed, int chargeAmount){
        try {
            int price = chargerService.preCalculateCost(stationName,speed,chargeAmount);
            SuccessView.messagePrint("[ "+stationName+" 충전소 ]에서 "+speed+"으로 충전할 경우, 예상 비용은 [ "+ price+"원 ]입니다.");
        }catch (SQLException | SearchWrongException | IncorrectInputException e) {
            FailView.errorMessage(e.getMessage());

            SessionSet sessionSet= SessionSet.getInstance();
            if(sessionSet.getSet().isEmpty()) nonUserFront.nonUserFrontview();
            else userFront.UserFrontview();
        }
    }
}
