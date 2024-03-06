package front;

import backend.controller.ReviewController;
import backend.controller.UsersController;
import backend.model.session.Session;
import backend.model.session.SessionSet;

import java.util.Scanner;

public class ReviewFront {
    public void ReviewFront(){
        UserFront front = new UserFront();
        boolean state = true;
        SessionSet sessionSet= SessionSet.getInstance();
        Session session = sessionSet.getCurrentSession(); // 또는 원하는 방법으로 세션을 선택
        String userId = session.getSessionId();
        int userNum = UsersController.searchByUserId(userId);
        Scanner sc = new Scanner(System.in);

        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │                   리뷰 서비스입니다. 원하시는 서비스를 선택해주세요.                   │");
            System.out.println("              │                1.리뷰 작성 || 2.리뷰 조회 || 3.리뷰 수정 || 4.처음으로              │");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘");

            int select = sc.nextInt();
            String stationName="";
            String content;
            int rate;

            switch (select) {
                case 1:
                    System.out.println("리뷰 작성 서비스입니다.");
                    System.out.print("충전소 이름 >");
                    stationName = sc.next();
                    System.out.print("충전소 리뷰 작성 >");
                    content = sc.next();
                    System.out.print("충전소 별점(5점 만점) >");
                    rate = sc.nextInt();
                    ReviewController.writeReviewService(userNum, stationName, content, rate);
                    break;
                case 2:
                    System.out.println("리뷰 조회 서비스입니다.");
                    System.out.println("1.충전소 별 리뷰 조회 || 2.내가 작성한 리뷰 조회");
                    int choose = sc.nextInt();
                    String group;

                    if(choose==1){ //충전소 별 리뷰 조회
                        group="station";
                        System.out.print("충전소 이름 >");
                        stationName = sc.next();
                    } else if (choose==2) { //사용자 리뷰 조회
                        group="users";
                    } else break;
                    ReviewController.searchReviewService(group,stationName, userNum);

                    //리뷰 정렬
                    System.out.println("              ┌──────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("              │                   조회하신 리뷰를 추가적으로 정렬하시겠습니까?                          │");
                    System.out.println("              │        1.별점 높은 순서 || 2.별점 낮은 순서 || 3.오래된 리뷰 순서 || 4.최신 리뷰 순서      │");
                    System.out.println("              │                5.리뷰 많은 충전소 순서 || 6.리뷰 적은 충전소 순서 || 7.처음으로          │");
                    System.out.println("              └──────────────────────────────────────────────────────────────────────────────┘");
                    int sort_standard = sc.nextInt();
                    String order;

                    switch (sort_standard){
                        case 1:
                            ReviewController.sortReviewByStandard(group,stationName,"RATE DESC",userNum,1);
                            break;
                        case 2:
                            ReviewController.sortReviewByStandard(group,stationName,"RATE ASC",userNum,0);
                            break;
                        case 3:
                            order="createDate ASC";
                            ReviewController.sortReviewByString(group,stationName,userNum,order);
                            break;
                        case 4:
                            order="createDate DESC";
                            ReviewController.sortReviewByString(group,stationName,userNum,order);
                            break;
                        case 5:
                            order="DESC";
                            ReviewController.sortReviewByStar(group,stationName,userNum,order);
                        case 6:
                            order="ASC";
                            ReviewController.sortReviewByStar(group,stationName,userNum,order);
                        case 7:
                            front.UserFrontview();
                            break;
                    }
                    break;
                case 3:
                    System.out.println("리뷰 수정 서비스입니다.");
                    System.out.println("아래 리뷰 중에서 수정할 리뷰 id를 선택해주세요.");
                    ReviewController.searchReviewService("users",stationName, userNum);
                    System.out.print("수정할 리뷰ID >");
                    int reviewId = sc.nextInt();
                    System.out.print("충전소 리뷰 재작성 >");
                    content = sc.next();
                    System.out.print("충전소 별점 재부여 > ");
                    rate = sc.nextInt();
                    ReviewController.updateReview(reviewId,content,rate);
                    break;
                case 4:
                    front.UserFrontview();
                    break;

            }

        }
    }
}
