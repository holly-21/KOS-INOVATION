package backend.model.dao;

import backend.model.dto.ReviewDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    //리뷰 작성
    @Override
    public int writeReview(int userNum, int stationId, String content, int star) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        String sql="insert into REVIEW values (rev_Seq.nextval,?,?,?,?,sysdate,sysdate)"; //(int reviewId, int userNum, int stationId, String content, int rate, Date createDate, Date fixDate)
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

    //충전소 별 리뷰 조회
    @Override
    public List<ReviewDto> searchReviewByStation(int stationId) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select * from review where stationId=?";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,stationId);

        }
        return null;
    }

    @Override
    public List<ReviewDto> searchReviewByUser(String userId) {
        return null;
    }

    @Override
    public List<ReviewDto> sortReviewByStandard(int standard, List<ReviewDto> review) {
        return null;
    }
}
