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
    public ChargerDto preCalculateCost(int stationId, String speed) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        String sql = "select kwPrice, speed from charger where STATIONID = ? and speed = ?";

        ChargerDto chargerDto=null;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,stationId);
            ps.setString(2,speed);

            rs = ps.executeQuery();

            if(rs.next()){
                chargerDto = new ChargerDto(rs.getInt(1),rs.getString(2));
            }
        } finally {
            DBManager.DbClose(con,ps,rs);
        }

        return chargerDto;
    }
}
