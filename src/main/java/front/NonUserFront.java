package front;

import backend.controller.UsersController;

import java.util.Scanner;

public class NonUserFront {
    public void NonUserFrontview() {
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        locFront locFront = new locFront();
        UserFront userFront = new UserFront();
        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │               ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '       │ ");
            System.out.println("              │                         서비스를 선택해주세요                                  │ ");
            System.out.println("              │              1.로그인 || 2. 회원가입 || 3.충전소검색 || 4.종료                   │ ");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘  ");

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
                    System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("              │                               로그인 서비스입니다                              │ ");
                    System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                    System.out.println("아이디를 입력해주세요:");
                    String checkid = sc.next();
                    UsersController.duplicateCheck(checkid);
                    String pwd = sc.next();

                    userFront.UserFrontview();
                    //입력된값을 로그인 함수에 입력해서 로그인 함수 호출

                case 2:
                    System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("              │                               회원가입 서비스입니다                            │ ");
                    System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                    System.out.println("아이디를 입력해주세요");
                    String id= sc.next();
                    //SelectById(id) 해서 null이면 패스워드 입력받고, null 이 아니면
                    // 중복된 회원이라고 메세지 출력.
                    System.out.println("비밀번호를 입력해주세요");
                    String pw= sc.next();
                    break;
                case 3:
                    locFront.locFront();

                    break;

                case 4:
                    state = false;
                    System.out.println("서비스를 종료합니다.");
                    break;

                default:
                    System.out.println("잘못된 입력어 입니다. ");
            }
        }

    }
}