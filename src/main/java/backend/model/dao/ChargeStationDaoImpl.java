package backend.model.dao;

import backend.model.dto.ChargeStationDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChargeStationDaoImpl implements ChargeStationDao{

    @Override // 기존의 메소드를 상속받아 재정의 한다.
    // Dao이기 때문에 DB와 관련이 있다.
    public List<ChargeStationDto> searchStation(String location) throws SQLException { // 인터페이스 메소드를 재정의
        List<ChargeStationDto> stations = new ArrayList<>(); // Dto를 List 형식으로 받아오는 stations 변수 정의
        Connection con = null; // Connection을 con 변수로 정의. java안에 Connection이 정의되어 있기 때문에 정의할 수 있다.
        PreparedStatement ps = null; // sql문을 전송하는 객체인 PreparedStatement를 ps 변수로 정의. java안에 PreparedStatement가 정의되어 있기 때문에 정의할 수 있다.
        ResultSet rs = null; // ResultSet을 rs 변수로 정의. java안에 ResultSet이 정의되어 있기 때문에 정의할 수 있다.
        String sql = "select * from CHARGESTATION where LOCATION LIKE ?"; // String 타입의 sql을 정의하여 쓰고 싶은 sql문 작성

        try { // 예외 처리를 위해 try-catch-finally 문법 사용!
            con = DBManager.getConnection(); // 위에 common된 Dbmanager에서 getConnection()을 con으로 정의
            ps = con.prepareStatement(sql); // con에서 sql문을 받는 prepareStatement을 ps로 정의
            ps.setString(1, "%" + location + "%"); // ps에서 setString을
            rs = ps.executeQuery();
            while (rs.next()) {
                ChargeStationDto station = new ChargeStationDto();
                station.setStationId(rs.getInt("STATIONID"));
                station.setLocation(rs.getString("LOCATION"));
                station.setPhone(rs.getString("PHONE"));
                station.setStationName(rs.getString("STATIONNAME"));
                stations.add(station);
            }
        } finally {
            DBManager.DbClose(con, ps, rs);
        }
        return stations;
    }

    @Override
    public List<ChargeStationDto> sortStationByStandard(int standard, List<ChargeStationDto> station) {
        return null;
    }


    //충전소 이름으로 찾기
    @Override // 오버라이드를 이용해서 인터페이스에 있는 메서드 재정의
    public int searchByStationName(String stationName) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select STATIONID from CHARGESTATION where STATIONNAME=?";
        int result = -1; // int 형식의 result 변수를 정의하고 -1 대입
        // -1을 넣은 이유: 아래에 있는 try가 동작이 되면

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, stationName); // 위의 쿼리문에서 ?안에 stationName 대입

            rs = ps.executeQuery();
            if (rs.next()) result = rs.getInt(1);
        } finally { // 파이널은 무조건 실행이 된다! 중간에 catch를 넣은 이유는 위에 throws를 이용해서 에러를 다음으로 던져줬기 때문이다.
            DBManager.DbClose(con, ps, rs);
        }
        return result;
    }
}
