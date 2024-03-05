package backend.controller;
import backend.service.UsersService;
import front.NonUserFront;

import java.sql.SQLException;

public class UsersController {

    static  NonUserFront nonUserFront= new NonUserFront();
     static UsersService usersService= new UsersService();


     public static void signUP(String id, String pw, String name){

         try {

             System.out.println(id+pw+name);
             usersService.signUp(pw,id,name);


         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

    public static void duplicateCheckForSignUp(String checkId){

        try {
            boolean idCheck=  usersService.duplicateCheck(checkId);
            if(idCheck ==true){
                System.out.println("중복된 아이디 입니다.");
                nonUserFront.signUp();


            }else {
                System.out.println("비밀번호를 입력해주세요");
            }


        } catch (SQLException e) {
            e.getMessage();
        }
    }



    public static void duplicateCheckForLogin(String checkId){

        try {
            boolean idCheck=  usersService.duplicateCheck(checkId);
            if(idCheck ==false){
                System.out.println("존재하지 않는 아이디입니다.");
                nonUserFront.login();


            }else {
                System.out.println("비밀번호를 입력해주세요");
            }


        } catch (SQLException e) {
            e.getMessage();
        }
    }


}
