package backend.model.dao;

import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecieptDaoImpl implements RecieptDao {
    //결제 내역 조회
    @Override
    public int SearchReceipt(String userId, String stationName) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from reciept where userId=? and stationName=?";
        int result = 0;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1,userId);
            ps.setString(2,stationName);

            rs = ps.executeQuery();
            if(rs.next()) result = 1;

        }finally {
            DBManager.DbClose(con,ps,rs);
        }
        return result;
    }

    @Override
    public int payCost(String userId, int balance, int expectCost) {
        return 0;
    }
}
