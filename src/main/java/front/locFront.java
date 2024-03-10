package front;

import backend.controller.ChargeStationController;
import backend.model.session.Session;
import backend.model.session.SessionSet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class locFront {
    public void locFront(){
        boolean state = true;

            Scanner sc = new Scanner(System.in);
        try {
            while (state) {
                NonUserFront nonUserFront= new NonUserFront();
                UserFront userFront = new UserFront();
                boolean state2 = true;

                System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                System.out.println("              │                             충전소 검색 서비스 입니다                          │ ");
                System.out.println("              │                                                                            │ ");
                System.out.println("              │    1. 위치 기반 검색|| 2. 이름 기반 검색 3. 회사 이름으로 검색 4. 로비로 돌아가기     │ ");
                System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                int select = sc.nextInt();
                switch (select) {
                    case 1:

                        System.out.print("충전소 위치 입력 > ");
                        String location = sc.next();
                        ChargeStationController.searchStationController(location);
                        System.out.println(" ┌─────────────────────────────┐");
                        System.out.println(" │계속 하고싶으면 1번을 클릭해주세요 │ ");
                        System.out.println(" └─────────────────────────────┘ ");
                        int input = sc.nextInt();
                        if (input == 1) {
                            locFront();
                        }
                        break;

                    case 2:
                        System.out.print("충전소 이름 입력 > ");
                        String stationName = sc.next();
                        ChargeStationController.searchByStationName(stationName);
                        System.out.println(" ┌─────────────────────────────┐");
                        System.out.println(" │계속 하고싶으면 1번을 클릭해주세요 │ ");
                        System.out.println(" └─────────────────────────────┘ ");
                        int input2 = sc.nextInt();
                        if (input2 == 1) {
                            locFront();
                        }
                        break;

                    case 4:
                        SessionSet sessionSet = SessionSet.getInstance();
                        if (sessionSet.getSet().isEmpty()) {
                            nonUserFront.nonUserFrontview();
                        } else {
                            userFront.UserFrontview();
                        }
                        break;

                    default:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                     잘못된 입력입니다. (1에서 4사이의 숫자만 입력해주세요 )         │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                }

            }
        }catch (InputMismatchException e){
            System.out.println("숫자만 입력해주세요");
            sc.nextLine();
            locFront();
        }
    }
}
