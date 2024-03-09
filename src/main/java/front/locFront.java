package front;

import backend.controller.ChargeStationController;

import java.util.Scanner;

public class locFront {
    public void locFront(){
        NonUserFront nonUserFront = new NonUserFront();
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        while (state) {

            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │                               충전소 검색 서비스 입니다                          │ ");
            System.out.println("              │                        검색하려는 하는 충전소의 지역명을 적어주세요                   │ ");
            System.out.println("              │                                  ex) '강남구'                                │ ");
            System.out.println("              │          '서울'로 입력하면 서울 지역 전체 충전소가 나오기 떄문에 추천드리지 않습니다.        │ ");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘ ");

            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.print("충전소 위치 입력 > ");
                    String location = sc.next();
                    ChargeStationController.searchStationController(location);
                    break;

                case 2:
                    System.out.print("충전소 이름 입력 > ");
                    String stationName = sc.nextLine();
                    ChargeStationController.searchByStationName(stationName);

                case 5:

                    nonUserFront.nonUserFrontview();

            }

        }
    }
}
