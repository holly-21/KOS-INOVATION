package front;

import backend.controller.ChargeStationController;
import backend.model.session.Session;
import backend.model.session.SessionSet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class locFront {
    Scanner sc = new Scanner(System.in);

    public void isContinue(){
        System.out.println(" ┌─────────────────────────────┐");
        System.out.println(" │ 계속 하고 싶으면 1번을 입력해주세요 │ ");
        System.out.println(" └─────────────────────────────┘ ");
        int input = sc.nextInt();
        if (input == 1) {
            locFront();
        }
    }
    public void locFront(){
        boolean state = true;

        try {
            while (state) {
                NonUserFront nonUserFront= new NonUserFront();
                UserFront userFront = new UserFront();

                System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                System.out.println("              │                              충전소 검색 서비스 입니다                           │ ");
                System.out.println("              │                                                                            │ ");
                System.out.println("              │     1.위치 기반 검색 || 2.이름 기반 검색 || 3.운영업체명으로 검색 || 4.로비로 돌아가기     │ ");
                System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                int select = sc.nextInt();
                switch (select) {
                    case 1:
                        System.out.print("충전소 위치 입력 > ");
                        String location = sc.next();
                        ChargeStationController.searchStationController(location);

                        isContinue();
                        break;

                    case 2:
                        System.out.print("충전소 이름 입력 > ");
                        String stationName = sc.next();
                        ChargeStationController.searchByStationName(stationName);

                        isContinue();
                        break;
                    case 3:
                        System.out.print("운영업체 이름 입력 > ");
                        String organizationName = sc.next();
                        ChargeStationController.searchByOraganizationName(organizationName);

                        isContinue();
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
                        System.out.println("              │                      잘못된 입력입니다. (1에서 4사이의 숫자만 입력해주세요 )           │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력해주세요");
            sc.nextLine();
            locFront();
        }
    }
}
