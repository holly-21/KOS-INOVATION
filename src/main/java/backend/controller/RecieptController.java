package backend.controller;

import backend.service.RecieptService;
import front.FailView;

import java.sql.SQLException;

public class RecieptController {

    static RecieptService recieptService = new RecieptService();


    public static void insertReciept (int userNum, int stationId, int chargeCost){
        try {
            recieptService.insertReciept( userNum, stationId,  chargeCost);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }

    }
}
