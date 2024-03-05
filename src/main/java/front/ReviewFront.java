package front;

import backend.controller.ReviewController;
import java.util.Scanner;

public class ReviewFront {
    public void ReviewFront(){
        UserFront front = new UserFront();
        boolean state = true;
        Scanner sc = new Scanner(System.in);

        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │                   리뷰 서비스입니다. 원하시는 서비스를 선택해주세요.                   │");
            System.out.println("              │                     1. 리뷰 작성 || 2. 리뷰 조회 || 3.처음으로                    │");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘");

            int select = sc.nextInt();
            String stationName="";

            switch (select) {
                case 1:
                    System.out.println("리뷰 작성 서비스입니다.");
                    System.out.print("충전소 이름 >");
                    stationName = sc.next();
                    System.out.print("충전소 리뷰 작성 >");
                    String content = sc.next();
                    System.out.print("충전소 별점 >");
                    int rate = sc.nextInt();
                    ReviewController.writeReviewService(1, stationName, content, rate); //TEST USER_NUM
                    break;
                case 2:
                    System.out.println("리뷰 조회 서비스입니다.");
                    System.out.println("1.충전소 별 리뷰 조회 || 2.내가 작성한 리뷰 조회");
                    int choose = sc.nextInt();
                    String group="";

                    if(choose==1){ //충전소 별 리뷰 조회
                        group="station";
                        System.out.print("충전소 이름 >");
                        stationName = sc.next();
                    } else if (choose==2) { //사용자 리뷰 조회
                        group="users";
                    } else break;
                    ReviewController.searchReviewService(group,stationName, 1); //TEST USER_NUM

                    //리뷰 정렬
                    System.out.println("조회하신 리뷰를 추가적으로 정렬하시겠습니까?");
                    System.out.println("1.별점 높은 순서 || 2.별점 낮은 순서 || 3.오래된 리뷰 순서 || 4.최신 리뷰 순서 || 5.처음으로");
                    int sort_standard = sc.nextInt();
                    String order;

                    switch (sort_standard){
                        case 1:
                            ReviewController.sortReviewByStandard(group,stationName,"RATE",1,1); //TEST USER_NUM
                            break;
                        case 2:
                            ReviewController.sortReviewByStandard(group,stationName,"RATE",1,0); //TEST USER_NUM
                            break;
                        case 3:
                            order="createDate ASC";
                            ReviewController.sortReviewByString(group,stationName,1,order);
                            break;
                        case 4:
                            order="createDate DESC";
                            ReviewController.sortReviewByString(group,stationName,1,order);
                            break;
                        case 5:
                            front.UserFrontview();
                            break;
                    }

                case 3:
                    front.UserFrontview();
                    break;

            }

        }
    }
}
