package backend.model.dao;

import backend.exception.DuplicateException;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecieptDaoImpl implements RecieptDao {
    //결제 내역 조회
    @Override
    public int SearchReceipt(int userNum, int stationId) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from RECEIPT where userNum=? and stationId=?";
        int result = 0;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1,userNum);
            ps.setInt(2,stationId);

            rs = ps.executeQuery();
            int cnt=0;
            while(rs.next()) {
                cnt++;
                if(cnt>1) throw new DuplicateException("결제 내역 1개당 하나의 리뷰만 작성할 수 있습니다.");
                result = 1;
            }


        }finally {
            DBManager.DbClose(con,ps,rs);
        }
        return result;
    }

    @Override
    public int payCost(String userId, int balance, int expectCost) {
        return 0;
    }

    @Override
    public List<Integer> selectReceiptOrderByCost() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> stationIds = new ArrayList<>();

        try {
            String sql = "SELECT STATIONID, SUM(CHARGECOST) AS totalChargeCost " +
                    "FROM RECEIPT " +
                    "GROUP BY STATIONID " +
                    "ORDER BY totalChargeCost DESC";


            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();


            while (rs.next()) {
                int stationId = rs.getInt("STATIONID");
                stationIds.add(stationId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.DbClose(con, ps, rs);
        }

        return stationIds;
    }
}
