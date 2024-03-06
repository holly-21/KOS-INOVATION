package front;

import backend.controller.ReviewController;
import backend.model.session.Session;
import backend.model.session.SessionSet;

import java.util.Scanner;

public class UserFront {


    public void UserFrontview() {
        boolean state = true;
        NonUserFront nonUserFront= new NonUserFront();
        SessionSet sessionSet= SessionSet.getInstance();
        Session session = sessionSet.getCurrentSession(); // 또는 원하는 방법으로 세션을 선택
        String userId = session.getSessionId();
        Scanner sc = new Scanner(System.in);
        locFront locFront = new locFront();
        ReviewFront ReviewFront = new ReviewFront();

        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │           ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '           │ ");
            System.out.println("              │                                     "                     +userId+"님 환영합니다                        │ ");
            System.out.println("              │                              서비스를 선택해주세요                             │ ");
            System.out.println("              │     1. 충전소 검색 || 2. 요금계산 || 3.테스트 || 4.테스트  || 5.리뷰  ||6.로그아웃       │ ");
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
                case 5:
                    System.out.println("테스트 review 구역입니다.");
                    ReviewFront.ReviewFront();
                    break;

                case 6:
                    System.out.println("로그아웃 되었습니다.");
                    sessionSet.remove(session);
                    nonUserFront.nonUserFrontview();
                    break;


            }
        }
    }
}
