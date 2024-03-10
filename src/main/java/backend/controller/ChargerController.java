package backend.controller;

import backend.exception.IncorrectInputException;
import backend.exception.SearchWrongException;
import backend.model.session.Session;
import backend.model.session.SessionSet;
import backend.service.ChargerService;
import front.*;

import java.sql.SQLException;
import java.util.Set;

public class ChargerController {
    static ChargerService chargerService = new ChargerService();
    static UserFront userFront= new UserFront();
    static NonUserFront nonUserFront = new NonUserFront();

    public static int preCalcCost(String stationName, String speed, int chargeAmount){

        try {
            int price = chargerService.preCalculateCost(stationName,speed,chargeAmount);
            SuccessView.messagePrint("[ "+stationName+" 충전소 ]에서 "+speed+"으로 충전할 경우, 비용은 [ "+ price+"원 ]입니다.");
            return price;
        }catch (SQLException | SearchWrongException | IncorrectInputException e) {
            FailView.errorMessage(e.getMessage());
            SessionSet sessionSet= SessionSet.getInstance();
            Set<Session> session = sessionSet.getSet();
            if(session.isEmpty()) nonUserFront.nonUserFrontview();
            else userFront.UserFrontview();

        }
        return 0;
    }
}
