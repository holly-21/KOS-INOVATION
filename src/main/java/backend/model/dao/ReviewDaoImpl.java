package backend.model.dao;

import backend.model.dto.ReviewDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    //리뷰 작성
    @Override
    public int writeReview(int userNum, int stationId, String content, int star) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        String sql="insert into REVIEW values (rev_Seq.nextval,?,?,?,?,sysdate,sysdate)";
        //(int reviewId, int userNum, int stationId, String content, int rate, Date createDate, Date fixDate)
        int result=0;

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
    public List<ReviewDto> sortReviewByStandard(int standard, int userNum) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<ReviewDto> list= new ArrayList<>();
        String sql = "select * from REVIEW where USERNUM=? order by ? desc";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,userNum);
            ps.setInt(2,standard); //칼럼 순서로 정렬
            ps.executeQuery();

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
}
