package front;

import backend.controller.ChargerController;
import backend.controller.ReviewController;
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
import backend.service.UsersService;

import java.util.List;
import java.util.Scanner;

public class UserFront {


    public void UserFrontview() {
        boolean state = true;
        NonUserFront nonUserFront = new NonUserFront();
        SessionSet sessionSet = SessionSet.getInstance();
        Session session = sessionSet.getCurrentSession(); // 또는 원하는 방법으로 세션을 선택
        String userId = session.getSessionId();
        Scanner sc = new Scanner(System.in);
        locFront locFront = new locFront();
        ReviewFront ReviewFront = new ReviewFront();
        int balance = UsersController.balanceStatus(userId);
        RecieptDao recieptDao = new RecieptDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();
        ReviewFront reviewFront= new ReviewFront();
        List<ChargeStationCostSumDto> list = recieptDao.selectReceiptOrderByCost();
        List<ChargeStationRateDto> avgList = reviewDao.chargeStationRateAvg();


        while (state) {
            System.out.println("              ┌───────────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │                              ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '                   │ ");
            System.out.println("              │                                            " + userId + "님 환영합니다                                            │ ");
            System.out.println("              │                                     " + userId + "님의 현재 코인 잔액은 " + balance + "코인 입니다.                             │ ");
            System.out.println("              │                                            서비스를 선택해주세요                                          │ ");
            System.out.println("              │       1.충전소 검색 || 2.요금계산 || 3.코인충전 || 4.리뷰 || 5.충전비용 사전계산|| 6.결제내역 조회|| 7.로그아웃    │ ");
            System.out.println("              └───────────────────────────────────────────────────────────────────────────────────────────────────────┘ ");
            for (int i = 0; i < 10; i++) {

                ChargeStationRateDto chargeStationRateDto = avgList.get(i);
                ChargeStationDto chargeStationDto = list.get(i);


                System.out.println("                                         "+(i + 1) + "위 " + chargeStationRateDto.getStationName() + "충전소 /업체명: " + chargeStationRateDto.getOrganization() +
                        " /평균평점:" + chargeStationRateDto.getAverageRate()) ;
            }
            System.out.println("                                    └====================================================================┘");
            System.out.println("                                   ┌====================================================================┐" );
            System.out.println("                                                           이번주 충전소 사용량 TOP 10       ");

            for (int i = 0; i < 10; i++) {
                ChargeStationCostSumDto chargeStationCostSumDto = list.get(i);
                System.out.println("                                         "+(i+1)+"위 "+ chargeStationCostSumDto.getStationName()+"충전소 /업체명: " + chargeStationCostSumDto.getOrganization()+"/ 총 사용금액:"+chargeStationCostSumDto.getCostSum()+" 원" );
            }
            System.out.println("                                    └====================================================================┘");


            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("테스트 1번 구역입니다.");
                    locFront.locFront();
                    break;

                case 2:
                    System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("              │                        충전 예상 비용 검색 서비스입니다                            │ ");
                    System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

                    ///////충전소 위치 조회 함수 불러오기//////

                    System.out.print("충전소 이름 입력 > ");
                    String stationName = sc.next();
                    System.out.print("충전 속도 입력(급속:faster/완속:lower) > ");
                    String speed = sc.next();
                    System.out.print("충전할 전기량 입력(단위:kwh) > ");
                    int chargeAmount = sc.nextInt();
                    ChargerController.preCalcCost(stationName, speed, chargeAmount);
                    break;


                case 3:
                    System.out.println("코인 충전소 입니다.");
                    System.out.println("원하는 충전량을 입력해주세요 \n 1코인은 한화 1원입니다.");
                    int coinQuantity = sc.nextInt();
                    UsersController.chargeCoin(userId, balance, coinQuantity);
                    UserFrontview();
                    break;

                case 4:
                    System.out.println("테스트 review 구역입니다.");
                    ReviewFront.ReviewFront();
                    break;

                case 6:
                    List<ReceiptDto> list2 = recieptDao.searchMyRecipt(userId);
                    int count2 =1;
                    for (ReceiptDto receiptDto : list2) {

                        System.out.println(" ┌=========================┐\n"+
                               "  "+ count2+"번 내역\n" +"  조회번호: "+receiptDto.getReceiptId() + "\n  충전소 아이디:" + receiptDto.getStationId() + "\n  충전한 지점명:"+receiptDto.getStationName()+"\n  충전 금액:"+receiptDto.getChargeCost()+"원"+receiptDto.getChargeDate()+"\n └=========================┘");
                            count2++;
                    }
                    boolean state2=true;
                    while (state2){
                        System.out.println(" ┌─────────────────────────────┐");
                        System.out.println(" │     로비로 돌아가기 : 1번      │ ");
                        System.out.println(" │     리뷰작성 페이지 : 2번      │ ");
                        System.out.println(" └─────────────────────────────┘ ");
                        int input = sc.nextInt();

                        if (input==1) {
                            state2 = false;
                            UserFrontview();
                        } else if (input==2) {
                            state2 = false;
                            reviewFront.ReviewFront();


                        }
                    }
                    break;

                case 7:
                    System.out.println("로그아웃 되었습니다.");
                    sessionSet.remove(session);
                    nonUserFront.nonUserFrontview();
                    break;

                default:
                    System.out.println("잘못된 입력입니다.");

            }
        }
    }
}
