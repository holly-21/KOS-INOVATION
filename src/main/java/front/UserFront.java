package front;

import backend.controller.ReviewController;

import java.util.Scanner;

public class UserFront {
    public void UserFrontview() {
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        locFront locFront = new locFront();
        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │           ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '           │ ");
            System.out.println("              │                        userDto.getUserName님 환영합니다                          │ ");
            System.out.println("              │                              서비스를 선택해주세요                             │ ");
            System.out.println("              │                   1. 충전소 검색 || 2. 요금계산 || 3.테스트 || 4.테스트          │ ");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
            System.out.println("  ┌===========================================┐" + "         ┌===========================================┐");
            System.out.println("           이번주 저렴한 충전소TOP 10       " + "                        이번주 충전소 사용량 TOP 10       ");
            for (int i = 0; i <= 10; i++) {
                System.out.println("             " + i + "순위" + "~~~ 충전소        " +
                        "  " + "                             " + i + "순위" + "~~~ 충전소             " + "  ");
            };
            System.out.println("  └===========================================┘" + "         └===========================================┘");

            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("테스트 1번 구역입니다.");
                    locFront.locFront();
                case 2:
                    System.out.println("review 작성 정보 입력");
                    System.out.print("충전소 이름 >");
                    String stationName = sc.next();
                    System.out.print("충전소 리뷰 작성 >");
                    String content = sc.next();
                    System.out.print("충전소 별점 >");
                    int rate = sc.nextInt();
                    ReviewController.writeReviewService(1, stationName, content, rate);
            }
        }
    }
}
