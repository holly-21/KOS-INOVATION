package backend.controller;
import backend.exception.DuplicateException;
import backend.service.UsersService;
import front.FailView;
import front.NonUserFront;

import java.sql.SQLException;

public class UsersController {

    static  NonUserFront nonUserFront= new NonUserFront();
     static UsersService usersService= new UsersService();


     public static void signUP(String id, String pw, String name){

         try {

             System.out.println(id+pw+name);
             boolean singup= usersService.signUp(pw,id,name);
             if(singup)
                 System.out.println("회원가입을 축하드립니다.");

         } catch (SQLException e) {
             FailView.errorMessage(e.getMessage());
         }
     }

    public static void duplicateCheckForSignUp(String checkId){

        try {
            boolean idCheck=  usersService.duplicateCheck(checkId);
            if(idCheck !=true){
                System.out.println("비밀번호를 입력해주세요");
            }

        } catch (DuplicateException e) {
            FailView.errorMessage(e.getMessage());
            nonUserFront.signUp();

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


        } catch (DuplicateException e) {
            e.getMessage();
        }
    }


}
