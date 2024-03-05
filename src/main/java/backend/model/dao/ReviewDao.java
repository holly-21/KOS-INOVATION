package backend.model.dao;

import backend.model.dto.ReviewDto;

import java.sql.SQLException;
import java.util.List;

public interface ReviewDao {
    /**
     * 리뷰 작성
     * (사용자 아이디, 충전소 위치, 결제 내역 아이디, 리뷰 내용, 별점)
     */
    int writeReview(int userNum, int stationId, String content, int rate) throws SQLException;

    /**
     * 리뷰 조회 - 사용자 or 충전소
     * @return 리뷰 리스트
     * (sequence, 사용자/충전소)
     */
    List<ReviewDto> searchReview(int Id, String group) throws SQLException;

    /**
     * 리뷰 정렬
     * @return 리뷰 리스트
     * (정렬 기준, 리뷰 리스트)
     */
    List<ReviewDto> sortReviewByStandard(String group,int id,String standard, int userId, int order) throws SQLException;

    List<ReviewDto> sortReviewByString(String group,int id,String order) throws SQLException;
}
