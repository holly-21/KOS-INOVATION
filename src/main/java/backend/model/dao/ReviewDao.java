package backend.model.dao;

import backend.model.dto.ChargeStationRateDto;
import backend.model.dto.ReviewDto;

import java.sql.SQLException;
import java.util.List;

public interface ReviewDao {
    /**
     * 리뷰 작성
     * (회원번호, 충전소 id, 리뷰 내용, 별점)
     */
    int writeReview(int userNum, int stationId, String content, int rate) throws SQLException;

    /**
     * 리뷰 조회 - 사용자 or 충전소
     * @return 리뷰 리스트
     * (sequence, 사용자/충전소)
     */
    List<ReviewDto> searchReview(int Id, String group) throws SQLException;

    /**
     * 리뷰 별점 기준 정렬
     * @return 리뷰 리스트
     * (사용자/충전소, 리뷰 리스트)
     */
    List<ReviewDto> sortReviewByStandard(String group,int id,String standard, int userId) throws SQLException;

    /**
     * 리뷰 등록일 기준 정렬
     * @return 리뷰 리스트
     * (사용자/충전소, 리뷰 리스트)
     */
    List<ReviewDto> sortReviewByString(String group,int id,String order) throws SQLException;

    /**
     * 리뷰 수 별 충전소 순서
     * @return 리뷰 리스트
     * (오름차순/내림차순)
     */
    List<ReviewDto> sortReviewByStar(String group,int id,String order) throws SQLException;

    /**
     * 리뷰 수정
     * (리뷰id,회원번호, 충전소id, 리뷰 내용, 별점)
     */
    int updateReview(int reviewId, String content, int rate) throws SQLException;


    List<ChargeStationRateDto> chargeStationRateAvg ();

}
