package backend.controller;

import backend.exception.DMLException;
import backend.exception.SearchWrongException;
import backend.model.dto.ReviewDto;
import backend.service.ReviewService;
import front.FailView;
import front.ReviewFront;
import front.SuccessView;
import front.UserFront;

import java.sql.SQLException;
import java.util.List;

public class ReviewController {
    private static ReviewService reviewService = new ReviewService();
    static ReviewFront reviewFront= new ReviewFront();

    //리뷰 작성
    public static void writeReviewService(int userNum, int receiptId, String content, int rate) {
        try{
            reviewService.writeReviewService(userNum, receiptId, content, rate);
            SuccessView.messagePrint("리뷰를 성공적으로 작성했습니다.");
        }catch (SearchWrongException | DMLException e){
            e.printStackTrace();
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        } catch (Exception e){
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        }
    }

    // 리뷰 조회
    public static void searchReviewService(String group, String Name, int userNum) {
        try{
            List<ReviewDto> reviewDtoList = reviewService.searchReviewService(group,Name, userNum);
            System.out.println(reviewDtoList);
        } catch (SearchWrongException e){
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        }
    }

    //리뷰 별점 정렬
    public static void sortReviewByStandard(String group,String stationName, String standard, int userNum){
        try{
            List<ReviewDto> reviewDtoList = reviewService.sortReviewByStandard(group,stationName,standard, userNum);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        }
    }

    //리뷰 생성날짜 기준 정렬
    public static void sortReviewByString(String group,String stationName, int userNum, String order){
        try{
            List<ReviewDto> reviewDtoList = reviewService.sortReviewByString(group,stationName,userNum,order);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        }
    }

    //리뷰 수 별 충전소 순서
    public static void sortReviewByStar(String group,String stationName, int userNum, String order){
        try{
            List<ReviewDto> reviewDtoList = reviewService.sortReviewByStar(group, stationName, userNum, order);
            System.out.println(reviewDtoList);
        }catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        }
    }

    //리뷰 수정
    public static void updateReview(int reviewId, String content, int rate) {
        try{
            reviewService.updateReview(reviewId, content, rate);
            SuccessView.messagePrint("리뷰를 성공적으로 수정했습니다.");
        }catch (DMLException e){
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        } catch (Exception e){
            FailView.errorMessage(e.getMessage());
            reviewFront.ReviewFront();
        }
    }
}
