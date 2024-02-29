package front;

import java.util.Scanner;

public class Front2 {
    public void front2(){
        UserFront front = new UserFront();
        boolean state = true;
        Scanner sc = new Scanner(System.in);
        while (state) {
            System.out.println("              ┌────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("              │                         '원하는 서비스를 입력해주세요 '                         │ ");
            System.out.println("              │       1.충전소 서비스 || 2.테스트 || 3.테스트 || 4.테스트 5.이전메뉴로 돌아가기. │ ");
            System.out.println("              └────────────────────────────────────────────────────────────────────────────┘  ");

            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("서울지역 충전소 검색 서비스입니다.");

                case 5:

                    front.UserFrontview();

            }

        }
    }
}
