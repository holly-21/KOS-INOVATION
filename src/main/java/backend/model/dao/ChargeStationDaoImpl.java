package backend.model.dao;

import backend.model.dto.ChargeStationDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChargeStationDaoImpl implements ChargeStationDao {

    @Override
    public List<ChargeStationDto> searchStation(String location) throws SQLException {
        List<ChargeStationDto> stations = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from CHARGESTATION where LOCATION LIKE ?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + location + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ChargeStationDto station = new ChargeStationDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                stations.add(station);
            }
        } finally {
            DBManager.DbClose(con, ps, rs);
        }
        return stations;
    }

    //충전소 이름으로 찾기
    @Override
    public int searchByStationName(String stationName) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select STATIONID from CHARGESTATION where STATIONNAME=?";
        int result = -1;

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, stationName);

            rs = ps.executeQuery();
            if (rs.next()) result = rs.getInt(1);
        } finally {
            DBManager.DbClose(con, ps, rs);
        }
        return result;
    }

    public List<ChargeStationDto> selectByStationName(String stationName) throws SQLException {
        List<ChargeStationDto> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from CHARGESTATION where STATIONNAME LIKE ?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + stationName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ChargeStationDto chargeStationDto = new ChargeStationDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(chargeStationDto);
            }
        } finally {
            DBManager.DbClose(con, ps, rs);
        }

        return list;
    }

    @Override
    public List<ChargeStationDto> searchByOraganizationName(String organizationName) throws SQLException {
        List<ChargeStationDto> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from CHARGESTATION where ORGANIZATION LIKE ?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + organizationName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ChargeStationDto chargeStationDto = new ChargeStationDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(chargeStationDto);
            }
        } finally {
            DBManager.DbClose(con, ps, rs);
        }
        return list;
    }
}
