package front;

import backend.controller.ReviewController;
import backend.controller.UsersController;
import backend.model.session.Session;
import backend.model.session.SessionSet;
import backend.service.UsersService;

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
        int balance =UsersController.balanceStatus(userId);


        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │           ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '           │ ");
            System.out.println("              │                             " + userId + "님 환영합니다                                │ ");
            System.out.println("              │                      " + userId + "님의 현재 코인 잔액은 " + balance + "코인 입니다.                  │ ");
            System.out.println("              │                              서비스를 선택해주세요                             │ ");
            System.out.println("              │    1.충전소 검색 || 2.요금계산 || 3.코인충전 || 4.테스트  || 5.리뷰 || 6.로그아웃   │ ");
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
                    break;

                case 2:



                case 3:
                    System.out.println("코인 충전소 입니다.");
                    System.out.println("원하는 충전량을 입력해주세요 \n 1코인은 한화 1원입니다.");
                    int coinQuantity = sc.nextInt();
                    UsersController.chargeCoin(userId, balance, coinQuantity );
                    break;

                case 4:
                    System.out.println("테스트 review 구역입니다.");
                    ReviewFront.ReviewFront();
                    break;

                case 5:
                    System.out.println("로그아웃 되었습니다.");
                    sessionSet.remove(session);
                    nonUserFront.nonUserFrontview();
                    break;

                default:
                    System.out.println("잘못된 입력입니다 . ");

            }
        }
    }
}
