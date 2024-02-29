package front;

import java.util.Scanner;

public class UserFront {
    public void UserFrontview() {
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        Front2 front2 = new Front2();
        while (state) {
            System.out.println("                                 ' KOS-이노베이션 전기자동차 충전소 서비스에 오신걸 환영합니다  '                                   ");
            System.out.println("                                                   서비스를 선택해주세요    ");
            System.out.println("                                       1. 충전소 검색 || 2. 요금계산 || 3.테스트 || 4.테스트  ");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("테스트 1번 구역입니다.");
                    front2.front2();
            }
        }
    }
}
