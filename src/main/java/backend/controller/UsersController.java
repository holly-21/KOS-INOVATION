package backend.controller;

import backend.exception.DuplicateException;
import backend.exception.IncorrectInputException;
import backend.exception.WrongTypeException;
import backend.model.dto.UsersDto;
import backend.service.UsersService;
import front.FailView;
import front.NonUserFront;
import front.SuccessView;

import java.sql.SQLException;

public class UsersController {

    static NonUserFront nonUserFront = new NonUserFront();
    static UsersService usersService = new UsersService();


    public static void signUP(String name, String id, String pw) {

        try {
            usersService.signUp(name, id, pw);

        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("ORA-12899")) {
                errorMessage = "사용자 비밀번호가 너무 깁니다\n 최대 길이는 영어,특수문자 포함 20자입니다.\n 다시 입력해주세요";
                FailView.errorMessage(errorMessage);
                nonUserFront.signUp();

            }
        }
    }



    public static void duplicateCheckForSignUp(String checkId) {

        try {
             usersService.duplicateCheck(checkId);
             SuccessView.messagePrint("비밀번호를 입력해주세요");


        } catch (DuplicateException e) {
            FailView.errorMessage(e.getMessage());
            nonUserFront.signUp();

        }
    }

    public static void login(String userId, String password){
        try {
            UsersDto usersDto = usersService.login(userId,password);
        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }


    }



    public static void duplicateCheckForLogin(String checkId) {

        try {
             usersService.duplicateCheck(checkId);


        } catch (DuplicateException e) {
            FailView.errorMessage(e.getMessage());
        }
    }


}
