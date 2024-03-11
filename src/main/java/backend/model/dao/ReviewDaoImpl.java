package backend.model.dao;

import backend.model.dto.ChargeStationRateDto;
import backend.model.dto.ReviewDto;
import common.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    //리뷰 작성
    @Override
    public int writeReview(int userNum, int stationId, String content, int star) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        String sql="insert into REVIEW values (rev_Seq.nextval,?,?,?,?,sysdate,sysdate)";
        int result;

        try{
            con = DBManager.getConnection();

            ps = con.prepareStatement(sql);
            ps.setInt(1,userNum);
            ps.setInt(2,stationId);
            ps.setString(3,content);
            ps.setInt(4,star);

            result = ps.executeUpdate();
        }finally {
            DBManager.releaseConnection(con,ps);
        }
        return result;
    }

    @Override
    public List<ReviewDto> searchReview(int Id, String group) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from review where "+group+"=?";
        List<ReviewDto> list = new ArrayList<>();

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,Id);

            rs = ps.executeQuery();

            while(rs.next()){
                ReviewDto reviewDto = new ReviewDto(rs.getInt(1),rs.getInt(2),rs.getInt(3),
                        rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                list.add(reviewDto);
            }

        }catch (SQLException e) {
            DBManager.DbClose(con,ps,rs);
        }
        return list;
    }

    @Override
    public List<ReviewDto> sortReviewByStandard(String group,int id,String standard, int userNum) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<ReviewDto> list= new ArrayList<>();
        String sql = "select * from REVIEW where "+group+"=? order by "+standard;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                ReviewDto reviewDto = new ReviewDto(rs.getInt(1),rs.getInt(2),rs.getInt(3),
                        rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                list.add(reviewDto);
            }

        }finally {
            DBManager.DbClose(con,ps,rs);
        }

        return list;
    }

    @Override
    public List<ReviewDto> sortReviewByString(String group, int id, String order) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<ReviewDto> list= new ArrayList<>();
        String sql = "SELECT stationId,CONTENT,RATE,CREATEDATE,FIXDATE as reviewCount FROM review where "+group+"=? GROUP BY stationId,CONTENT,RATE,CREATEDATE,FIXDATE ORDER BY "+order;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);

            rs = ps.executeQuery();

            while(rs.next()){
                ReviewDto reviewDto = new ReviewDto(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4), rs.getString(5));
                list.add((reviewDto));
            }

        }finally {
            DBManager.DbClose(con,ps,rs);
        }
        return list;
    }

    //리뷰 수 별 충전소 순서
    @Override
    public List<ReviewDto> sortReviewByStar(String group,int id, String order) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<ReviewDto> list= new ArrayList<>();
//        String sql = "SELECT r.*, (select count(STATIONID) from review where STATIONID=r.stationId) as cs " +
//                "from REVIEW r " +
//                "where r."+group+"=?"+
//                " order by cs "+order;
        String sql = "SELECT r.*, " +
                "       (select count(STATIONID) from review where "+group+"=?"+" and STATIONID=r.stationId group by STATIONID) as cs " +
                "from REVIEW r " +
                "where "+group+"=?"+
                "order by cs "+order+" , STATIONID asc";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,id);

            rs = ps.executeQuery();

            while(rs.next()){
                ReviewDto reviewDto = new ReviewDto(rs.getInt(1),rs.getInt(2),rs.getInt(3),
                        rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                list.add((reviewDto));
            }

        }finally {
            DBManager.DbClose(con,ps,rs);
        }

        return list;
    }

    //리뷰 수정
    @Override
    public int updateReview(int reviewId, String content, int rate) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        String sql="update REVIEW set content=?, rate=?,FIXDATE=sysdate where reviewId=?";
        int result;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,content);
            ps.setInt(2,rate);
            ps.setInt(3,reviewId);

            result = ps.executeUpdate();
        }finally {
            DBManager.releaseConnection(con,ps);
        }
        return result;
    }

    @Override
  public List<ChargeStationRateDto> chargeStationRateAvg (){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs = null;
        List<ChargeStationRateDto>list = new ArrayList<>();
        String sql = "SELECT cs.*, ROUND(AVG(r.rate), 1) AS averageRate " +
                "FROM review r " +
                "JOIN chargeStation cs ON r.stationId = cs.stationId " +
                "GROUP BY r.stationId, cs.stationName, cs.stationId, cs.organization, cs.location, cs.phone " +
                "ORDER BY averageRate DESC";

        try {
            con= DBManager.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                ChargeStationRateDto chargeStationRateDto= new ChargeStationRateDto(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
            list.add(chargeStationRateDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.DbClose(con,ps,rs);
        }

        return list;
    }
}
