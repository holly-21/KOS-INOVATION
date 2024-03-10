package backend.model.dao;

import backend.exception.DuplicateException;
import backend.model.dto.ReceiptDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import backend.model.dto.ChargeStationCostSumDto;


public class RecieptDaoImpl implements RecieptDao {
    //결제 내역 당 하나의 리뷰만 작성할 수 있다.
    @Override
    public int isDuplicate(int userNum, int stationId) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from REVIEW where userNum=? and stationId=?";
        int result = 0; //정상

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1,userNum);
            ps.setInt(2,stationId);

            rs = ps.executeQuery();
            if(rs.next())result=1; //비정상

        }finally {
            DBManager.DbClose(con,ps,rs);
        }
        return result;
    }

    @Override
    public int SearchReceipt(int receiptId) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        String sql="select stationId from RECEIPT where RECEIPTID=?";
        int stationId = -1;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1,receiptId);

            rs = ps.executeQuery();
            int cnt=0;
            while(rs.next()) {
                cnt++;
                if(cnt>1) throw new DuplicateException("결제 내역 1개당 하나의 리뷰만 작성할 수 있습니다.");
                stationId = rs.getInt(1);
            }
        }finally {
            DBManager.DbClose(con,ps,rs);
        }
        return stationId;
    }

    @Override
    public  List<ReceiptDto> searchMyRecipt(String userId){
        Connection con= null;
        PreparedStatement ps= null;
        ResultSet rs = null;
        List<ReceiptDto> list = new ArrayList<>();
        String sql = "SELECT r.*, cs.stationName " +
                "FROM receipt r " +
                "JOIN chargeStation cs ON r.stationId = cs.stationId " +
                "WHERE r.userNum = (SELECT userNum FROM users WHERE userId = ?)";

        try {
            con= DBManager.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1,userId);
            rs= ps.executeQuery();

            while (rs.next()){
                ReceiptDto receiptDto= new ReceiptDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5) , rs.getString(6));
                list.add(receiptDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.DbClose(con,ps,rs);
        }

        return list;
    }


    @Override
    public int payCost(int userNum, int stationId, int chargeCost ) throws SQLException {
        Connection con = null;
        PreparedStatement ps= null;
        String sql= "insert into RECEIPT (RECEIPTID, USERNUM, STATIONID, CHARGECOST, CHARGEDATE) VALUES" +
                "    (REC_SEQ.nextval, ?,?,?,sysdate )";
        int result;

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userNum);
            ps.setInt(2, stationId);
            ps.setInt(3, chargeCost);
            result=ps.executeUpdate();
        }finally {
            DBManager.releaseConnection(con,ps);
        }
        return result;
    }

    @Override
    public List<ChargeStationCostSumDto> selectReceiptOrderByCost() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChargeStationCostSumDto> list = new ArrayList<>();

        try {

            String sql = "SELECT cs.STATIONID, cs.ORGANIZATION, cs.LOCATION, cs.PHONE,  cs.stationName,  SUM(r.chargeCost) AS totalChargeCost" +
                    "                    FROM receipt r" +
                    "                    JOIN chargeStation cs ON r.stationId = cs.stationId" +
                    "                    GROUP BY r.stationId, cs.stationName, cs.ORGANIZATION, cs.STATIONID, cs.LOCATION, cs.PHONE" +
                    "                    ORDER BY totalChargeCost DESC";

            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChargeStationCostSumDto chargeStationCostSumDto= new ChargeStationCostSumDto(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6));
                list.add(chargeStationCostSumDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.DbClose(con, ps, rs);
        }

        return list;
    }
}
