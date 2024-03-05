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
            System.out.println("              │                   리뷰 서비스입니다. 원하시는 서비스를 선택해주세요.                   │ ");
            System.out.println("              │               1. 리뷰 작성 || 2. 리뷰 조회 || 3.테스트 || 4.처음으로            │ ");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("리뷰 작성 서비스입니다.");
                    System.out.print("충전소 이름 >");
                    String stationName = sc.next();
                    System.out.print("충전소 리뷰 작성 >");
                    String content = sc.next();
                    System.out.print("충전소 별점 >");
                    int rate = sc.nextInt();
                    ReviewController.writeReviewService(1, stationName, content, rate);
                    break;
                case 4:
                    front.UserFrontview();
                    break;

            }

        }
    }
}
