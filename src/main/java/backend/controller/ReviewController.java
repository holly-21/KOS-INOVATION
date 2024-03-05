package backend.controller;

import backend.model.dto.ReviewDto;
import backend.service.ReviewService;

import java.sql.SQLException;
import java.util.List;

public class ReviewController {
    private static ReviewService reviewService = new ReviewService();


    //리뷰 작성
    public static void writeReviewService(int userNum, String stationName, String content, int star) {
        try{
            reviewService.writeReviewService(userNum, stationName, content, star);
            System.out.println("리뷰를 성공적으로 작성했습니다.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 충전소 별 리뷰 조회
    public static void searchReviewStService(String stationName) throws SQLException {
        try{
            List<ReviewDto> reviewDtoList = reviewService.searchReviewStService(stationName);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 리뷰 조회
    public static void searchReviewService(String group, String Name, int userNum) {
        try{
            List<ReviewDto> reviewDtoList = reviewService.searchReviewService(group,Name, userNum);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //리뷰 정렬
    public static void sortReviewByStandard(int standard, int userNum){
        try{
            List<ReviewDto> reviewDtoList = reviewService.sortReviewByStandard(standard, userNum);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
