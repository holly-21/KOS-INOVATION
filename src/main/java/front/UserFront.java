package front;

import backend.controller.ChargeStationController;
import backend.controller.RecieptController;
import backend.controller.UsersController;
import backend.model.dao.RecieptDao;
import backend.model.dao.RecieptDaoImpl;
import backend.model.dao.ReviewDao;
import backend.model.dao.ReviewDaoImpl;
import backend.model.dto.ChargeStationCostSumDto;
import backend.model.dto.ChargeStationDto;
import backend.model.dto.ChargeStationRateDto;
import backend.model.dto.ReceiptDto;
import backend.model.session.Session;
import backend.model.session.SessionSet;
import backend.service.ChargeStationService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserFront {
    private int balance;

    public void setUserBalance(int balance) {
        this.balance = balance;
    }

    public void UserFrontview() {
        boolean state = true;
        NonUserFront nonUserFront = new NonUserFront();
        SessionSet sessionSet = SessionSet.getInstance();
        Session session = sessionSet.getCurrentSession(); // 또는 원하는 방법으로 세션을 선택
        String userId = session.getSessionId();
        Scanner sc = new Scanner(System.in);
        locFront locFront = new locFront();
        ReviewFront ReviewFront = new ReviewFront();
        balance = UsersController.balanceStatus(userId);
        RecieptDao recieptDao = new RecieptDaoImpl();
        ChargeStationService chargeStationService = new ChargeStationService();
        ReviewDao reviewDao = new ReviewDaoImpl();
        ReviewFront reviewFront = new ReviewFront();
        List<ChargeStationCostSumDto> list = recieptDao.selectReceiptOrderByCost();
        List<ChargeStationRateDto> avgList = reviewDao.chargeStationRateAvg();

        try {
            while (state) {
                System.out.println("              ┌───────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println("              │                              ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '                   │ ");
                System.out.println("              │                                             " + userId + "님 환영합니다                                            │ ");
                System.out.println("              │                                     " + userId + "님의 현재 코인 잔액은 " + balance + "코인 입니다.                             │ ");
                System.out.println("              │                                             서비스를 선택해주세요                                         │ ");
                System.out.println("              │       1.충전소 검색 || 2.요금 결제 || 3.코인충전 || 4.리뷰 || 5.충전비용 사전계산 || 6.결제내역 조회|| 7.로그아웃   │ ");
                System.out.println("              └───────────────────────────────────────────────────────────────────────────────────────────────────────┘ ");
                System.out.println("                                   ┌====================================================================┐");
                for (int i = 0; i < 10; i++) {
                    ChargeStationRateDto chargeStationRateDto = avgList.get(i);
                    ChargeStationDto chargeStationDto = list.get(i);


                    System.out.println("                                         " + (i + 1) + "위 " + chargeStationRateDto.getStationName() + "충전소 /업체명: " + chargeStationRateDto.getOrganization() +
                            " /평균평점:" + chargeStationRateDto.getAverageRate());
                }
                System.out.println("                                    └====================================================================┘");
                System.out.println("                                    ┌====================================================================┐");
                System.out.println("                                                           이번주 충전소 사용량 TOP 10       ");

                for (int i = 0; i < 10; i++) {
                    ChargeStationCostSumDto chargeStationCostSumDto = list.get(i);
                    System.out.println("                                         " + (i + 1) + "위 " + chargeStationCostSumDto.getStationName() + "충전소 /업체명: " + chargeStationCostSumDto.getOrganization() + "/ 총 사용금액:" + chargeStationCostSumDto.getCostSum() + " 원");
                }
                System.out.println("                                    └====================================================================┘");


                int select = sc.nextInt();
                switch (select) {
                    case 1:
                        locFront.locFront();
                        break;

                    case 2:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                              요금 결제 서비스입니다.                           │ ");
                        System.out.println("              │                           현재 잔액은 " + balance + "코인 입니다                        │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                        System.out.print("충전소 이름 검색 > ");
                        String tempStation = sc.next();
                        ChargeStationController.searchByStationName(tempStation);

                        System.out.print("충전소 이름 입력 > ");
                        String stationName = sc.nextLine();
                        int userCost = nonUserFront.calcCharge(stationName);
                        userCost = userCost * -1;
                        System.out.println(userCost);
                        UsersController.chargeCoin(userId, balance, userCost);
                        int userNUm = UsersController.searchByUserId(userId);
                        ChargeStationDto chargeStationDto = (ChargeStationDto) chargeStationService.searchByStationName(stationName);
                        int stationId = chargeStationDto.getStationId();
                        System.out.println(userNUm + stationId + balance);

                        RecieptController.insertReciept(userNUm, stationId, balance);


                        UserFrontview();
                        break;

                    case 3:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                              코인 충전소 입니다.                              │ ");
                        System.out.println("              │                          원하는 충전량을 입력해주세요                           │ ");
                        System.out.println("              │                             1코인은 한화 1원입니다.                            │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                        int coinQuantity = sc.nextInt();
                        UsersController.chargeCoin(userId, balance, coinQuantity);
                        UserFrontview();
                        break;

                    case 4:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                              리뷰 서비스입니다.                               │ ");
                        System.out.println("              │                              엔터를 눌러주세요.                               │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                        ReviewFront.ReviewFront();
                        break;
                    case 5:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                        충전 예상 비용 검색 서비스입니다                          │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                        //////충전소 위치 조회 함수 불러오기///////
                        System.out.print("충전소 이름 입력 > ");
                        String stationName2 = sc.nextLine();
                        nonUserFront.calcCharge(stationName2);


                        break;

                    case 6:
                        List<ReceiptDto> list2 = recieptDao.searchMyRecipt(userId);
                        int count2 = 1;
                        for (ReceiptDto receiptDto : list2) {

                            System.out.println(" ┌=========================┐\n" +
                                    "  " + count2 + "번 내역\n" + "  조회번호: " + receiptDto.getReceiptId() + "\n  충전소 아이디:" + receiptDto.getStationId() + "\n  충전한 지점명:" + receiptDto.getStationName() + "\n  충전 금액:" + receiptDto.getChargeCost() + "원" + receiptDto.getChargeDate() + "\n └=========================┘");
                            count2++;
                        }

                        reviewFront.navigate();
                        break;

                    case 7:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                              로그아웃 되었습니다.                               │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                        sessionSet.remove(session);
                        nonUserFront.nonUserFrontview();
                        break;

                    default:
                        System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                        System.out.println("              │                     잘못된 입력입니다. (1에서 7사이의 숫자만 입력해주세요 )         │ ");
                        System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");
                }
            }
        } catch (Exception e) {
            System.out.println("숫자만 입력해주세요");
            sc.nextLine();
            UserFrontview();
        }
    }
}
