package backend.controller;

import backend.model.dto.ReviewDto;
import backend.service.ReviewService;
import front.FailView;

import java.sql.SQLException;
import java.util.List;

public class ReviewController {
    private static ReviewService reviewService = new ReviewService();


    //리뷰 작성
    public static void writeReviewService(int userNum, String stationName, String content, int rate) {
        try{
            reviewService.writeReviewService(userNum, stationName, content, rate);
            System.out.println("리뷰를 성공적으로 작성했습니다.");
        }catch (Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }

    // 리뷰 조회
    public static void searchReviewService(String group, String Name, int userNum) {
        try{
            List<ReviewDto> reviewDtoList = reviewService.searchReviewService(group,Name, userNum);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    //리뷰 정렬
    public static void sortReviewByStandard(String group,String stationName, String standard, int userNum, int order){
        try{
            List<ReviewDto> reviewDtoList = reviewService.sortReviewByStandard(group,stationName,standard, userNum, order);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void sortReviewByString(String group,String stationName, int userNum, String order){
        try{
            List<ReviewDto> reviewDtoList = reviewService.sortReviewByString(group,stationName,userNum,order);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }
}
