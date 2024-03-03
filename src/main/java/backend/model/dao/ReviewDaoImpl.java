package backend.model.dao;

import backend.model.dto.ReviewDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    //리뷰 작성
    @Override
    public void writeReview(String userId, String location, String receiptId, String Content, int star) {
//        Connection con=null;
//        PreparedStatement ps=null;
//        String sql="insert into REVIEW value(?,?,?,?,?,?")"; //(int reviewId, int stationId, String content, int rate, Date createDate, Date fixDate)
//
//        try{
//            con = DBManager.getConnection();
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1,userId);
//            ps.setString(2,location);
//            ps.setString(3,receiptId);
//
//        }
    }

    @Override
    public List<ReviewDto> searchReviewByStation(String stationName) {
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
