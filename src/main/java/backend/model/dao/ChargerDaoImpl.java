package backend.model.dao;

import backend.model.dto.ChargerDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChargerDaoImpl implements ChargerDao {
    /**
     * 킬로와트 당 비용 조회
     * @return 충전기기 정보
     * (충전소 위치, 충전 속도, 충전량)
     */
    @Override
    public ChargerDto preCalculateCost(String location, String speed, int chargeAmount) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        String sql = "select kwPrice, speed from charger as c " +
                "inner join chargestation as cs on c.stationid = cs.stationid " +
                "where cs.location = ? and speed = ?";

        ChargerDto charger=null;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,location);
            ps.setString(2,speed);

            rs = ps.executeQuery();

            if(rs.next()){
                charger = new ChargerDto(rs.getInt(1),rs.getInt(2));
            }
        } finally {
            DBManager.DbClose(con,ps,rs);
        }

        return charger;
    }
}
