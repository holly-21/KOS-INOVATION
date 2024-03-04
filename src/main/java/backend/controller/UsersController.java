package backend.controller;

import backend.model.dao.UsersDao;
import backend.model.dao.UsersDaoImpl;
import backend.model.dto.UsersDto;
import backend.service.UsersService;
import front.NonUserFront;

import java.sql.SQLException;

public class UsersController {

     static UsersService usersService= new UsersService();


    public static void duplicateCheck(String checkId){

        try {
            boolean idCheck=  usersService.duplicateCheck(checkId);
            if(idCheck ==false){
                System.out.println("존재하지 않는 아이디입니다.");

            }else {
                System.out.println("존재하는 아이디입니다");

            }


        } catch (SQLException e) {
            e.getMessage();
        }
    }


}
