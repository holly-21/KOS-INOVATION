package backend.controller;

import backend.exception.DuplicateException;

import backend.exception.SearchWrongException;
import backend.model.dto.UsersDto;
import backend.service.UsersService;
import front.FailView;
import front.NonUserFront;
import front.SuccessView;
import front.UserFront;

import java.sql.SQLException;

public class UsersController {

    static NonUserFront nonUserFront = new NonUserFront();
    static UserFront userFront = new UserFront();
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
            SuccessView.messagePrint(" 사용가능한 아이디 입니다. \n비밀번호를 입력해주세요 ");
        } catch (DuplicateException e) {
            FailView.errorMessage(e.getMessage());
            nonUserFront.signUp();

        }
    }

    public static void login(String userId, String password) {
        try {
            UsersDto usersDto = usersService.login(userId, password);
            SuccessView.messagePrint("로그인에 성공하였습니다");
            userFront.UserFrontview();

        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
            nonUserFront.login();
        }
    }

    public static void chargeCoin(String userId, int balance, int coinQuantity) {
        try {
            usersService.chargeCoin(userId, balance, coinQuantity);
        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static int balanceStatus(String userId) {
        int balance = 0;
        try {
            balance = usersService.searcBalanceByUserId(userId);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
        return balance;
    }


    public static int searchByUserId(String userId) {
        int userNum = -1;
        try {
            userNum = usersService.searchByUserId(userId);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
        return userNum;
    }


}
