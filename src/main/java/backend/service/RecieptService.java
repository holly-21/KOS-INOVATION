package backend.service;

import backend.model.dao.RecieptDao;
import backend.model.dao.RecieptDaoImpl;

import java.sql.SQLException;

public class RecieptService {

    RecieptDao recieptDao = new RecieptDaoImpl();
    public int insertReciept(int userNum, int stationId, int chargeCost) throws SQLException {
        int result =  recieptDao.payCost(userNum, stationId, chargeCost);
        throw new SQLException();
    }
}
