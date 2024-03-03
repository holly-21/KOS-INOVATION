package backend.model.dao;

import backend.model.dto.ChargeStationDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChargeStationDaoImpl implements ChargeStationDao{

    @Override
    public List<ChargeStationDto> searchStation(String location) {
        return null;
    }

    @Override
    public List<ChargeStationDto> sortStationByStandard(int standard, List<ChargeStationDto> station) {
        return null;
    }


    @Override
    public int searchByStationName(String stationName) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select stationId from CHARGESTATION where stationName=?";
        int result = -1;

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,stationName);
            rs=ps.executeQuery();
            if(rs.next()) result = rs.getInt(1);
        }finally {
            DBManager.DbClose(con, ps, rs);
        }
        return result;
    }
}
