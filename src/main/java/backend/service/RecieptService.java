package backend.service;

import backend.model.dao.RecieptDao;
import backend.model.dao.RecieptDaoImpl;
import backend.model.dto.ReceiptDto;
import front.SuccessView;

import java.sql.SQLException;

public class RecieptService {

    RecieptDao recieptDao = new RecieptDaoImpl();
    public int insertReciept(int userNum, int stationId, int chargeCost) throws SQLException {
        int result =  recieptDao.payCost(userNum, stationId, chargeCost);
        if (result==1){

        }throw new SQLException();
    }
}
