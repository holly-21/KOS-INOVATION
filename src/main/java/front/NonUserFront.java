package front;

import backend.controller.UsersController;

import java.util.Scanner;

public class NonUserFront {


    Scanner sc = new Scanner(System.in);
    locFront locFront = new locFront();
    UserFront userFront = new UserFront();

    public String login(){
        System.out.println("아이디를 입력해 주세요. ");
      String checkId= sc.next();
        UsersController.duplicateCheckForLogin(checkId);

        return checkId;
    };

    public String signUp(){
        System.out.println("아이디를 입력해 주세요. ");
        String checkId= sc.next();
        UsersController.duplicateCheckForSignUp(checkId);

        return checkId;
    };






    public void nonUserFrontview() {
        boolean state = true;

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
                    String id=login();

                    String pw= sc.next();




                    userFront.UserFrontview();
                    //입력된값을 로그인 함수에 입력해서 로그인 함수 호출

                case 2:
                    System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("              │                               회원가입 서비스입니다                            │ ");
                    System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                    System.out.println("이름을 입력해주세요. ");
                    String name= sc.next();
                    String checkId= signUp();
                    String password= sc.next();
                    UsersController.signUP(checkId,password,name);

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