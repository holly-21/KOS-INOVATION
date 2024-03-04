package backend.controller;

import backend.service.ReviewService;

import java.sql.SQLException;

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
}
