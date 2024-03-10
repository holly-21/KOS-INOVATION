package front;

import backend.controller.ChargeStationController;
import backend.controller.ChargerController;
import backend.controller.RecieptController;
import backend.controller.UsersController;
import backend.exception.SearchWrongException;
import backend.model.dao.*;
import backend.model.dto.ChargeStationCostSumDto;
import backend.model.dto.ChargeStationDto;
import backend.model.dto.ChargeStationRateDto;
import backend.model.dto.ReceiptDto;
import backend.model.session.Session;
import backend.model.session.SessionSet;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NonUserFront {


    Scanner sc = new Scanner(System.in);
    locFront locFront = new locFront();
    RecieptDao recieptDao = new RecieptDaoImpl();
    ReviewDao reviewDao = new ReviewDaoImpl();
    List<ChargeStationCostSumDto> list = recieptDao.selectReceiptOrderByCost();
    List<ChargeStationRateDto> avgList = reviewDao.chargeStationRateAvg();

    public int calcCharge(String stationName) {
        sc.nextLine();
        System.out.println("받아와라좀"+stationName);
        System.out.print("충전 속도 입력(급속:faster/완속:lower) > ");
        String speed = sc.nextLine();
        System.out.print("충전할 전기량 입력(단위:kwh) > ");
        int chargeAmount = sc.nextInt();
        int cost = ChargerController.preCalcCost(stationName, speed, chargeAmount);
        return cost;
    }

    public void login() {
        System.out.println("아이디를 입력해 주세요. ");
        String id = sc.next();
        System.out.println("비밀번호를 입력해주세요 ");
        String pw = sc.next();
        UsersController.login(id, pw);

    }


    public String signUp() {
        System.out.println("이름을 입력해주세요. ");
        String name = sc.next();
        System.out.println("아이디를 입력해주세요. ");
        String checkId = sc.next();
        UsersController.duplicateCheckForSignUp(checkId);
        String password = sc.next();
        UsersController.signUP(name, checkId, password);
        return checkId;
    }


    public void nonUserFrontview() {

        try {
            boolean state = true;
            while (state) {
                System.out.println("                                 ┌────────────────────────────────────────────────────────────────────────────┐");
                System.out.println("                                 │                 ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '     │ ");
                System.out.println("                                 │                             서비스를 선택해주세요.                             │ ");
                System.out.println("                                 │       1.로그인 || 2. 회원가입 || 3.충전소검색 || 4.충전 예상 비용 계산 || 5.종료    │ ");
                System.out.println("                                 └────────────────────────────────────────────────────────────────────────────┘  ");

                System.out.println("                                   ┌====================================================================┐");
                System.out.println("                                                        이번주 충전소 이용리뷰 별점 충전소TOP 10         ");
                for (int i = 0; i < 10; i++) {

                    ChargeStationRateDto chargeStationRateDto = avgList.get(i);
                    ChargeStationDto chargeStationDto = list.get(i);


                    System.out.println("                                         " + (i + 1) + "위 " + chargeStationRateDto.getStationName() + "충전소 /업체명: " + chargeStationRateDto.getOrganization() +
                            " /평균평점:" + chargeStationRateDto.getAverageRate());
                }
                System.out.println("                                    └====================================================================┘");
                System.out.println("                                   ┌====================================================================┐");
                System.out.println("                                                           이번주 충전소 사용량 TOP 10       ");

                for (int i = 0; i < 10; i++) {
                    ChargeStationCostSumDto chargeStationCostSumDto = list.get(i);
                    System.out.println("                                         " + (i + 1) + "위 " + chargeStationCostSumDto.getStationName() + "충전소 /업체명: " + chargeStationCostSumDto.getOrganization() + "/ 총 사용금액:" + chargeStationCostSumDto.getCostSum() + " 원");
                }
                System.out.println("                                    └====================================================================┘");


                int select = sc.nextInt();
                switch (select) {
                    case 1:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                               로그인 서비스입니다                              │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                        login();
                        break;


                    //입력된값을 로그인 함수에 입력해서 로그인 함수 호출

                    case 2:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                               회원가입 서비스입니다                            │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                        signUp();

                        break;
                    case 3:
                        locFront.locFront();

                        break;

                    case 4:
                        sc.nextLine();
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                        충전 예상 비용 검색 서비스입니다                          │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                        ///////충전소 위치 조회 함수 불러오기//////
                        System.out.print("충전소 이름 입력 > ");
                        String stationName = sc.nextLine();
                        calcCharge(stationName);
                        break;
                    case 5:
                        state = false;
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                              서비스를 종료합니다.                              │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                        System.exit(0);
                        break;
                    case 6:
                        SessionSet sessionSet = SessionSet.getInstance();
                        sessionSet.getSet();
                        System.out.println(sessionSet.getSet());
                        break;

                    case 7:
                        int userNum = 1;
                        int stationId = 28;
                        int chargecost = 200;
                        RecieptController.insertReciept(userNum, stationId, chargecost);
                        System.out.println("test");
                    default:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                     잘못된 입력입니다. (1에서 5사이의 숫자만 입력해주세요 )         │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력해주세요.");
            sc.nextLine();
            nonUserFrontview();
        }

    }
}