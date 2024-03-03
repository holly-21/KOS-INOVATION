package backend.controller;

import backend.service.ReviewService;

import java.sql.SQLException;

public class ReviewController {
    private static ReviewService reviewService = new ReviewService();


    //리뷰 작성
//    public int writeReviewService(int userNum, String stationName, String content, int star) {
//        try{
//            reviewService.writeReviewService(userNum, stationName, content, star);
//            EndView.SuccessMessage("리뷰를 성공적으로 작성했습니다.");
//        }catch (Exception e){
//            FailView.errorMessage(e.getMessage());
//        }
//    }
}
