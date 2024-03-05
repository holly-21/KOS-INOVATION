package backend.service;

import backend.exception.DuplicateException;
import backend.exception.SearchWrongException;
import backend.model.dao.UsersDao;
import backend.model.dao.UsersDaoImpl;

import java.sql.SQLException;

public class UsersService {

    UsersDao usersDao =new UsersDaoImpl();


    public boolean signUp(String id, String pw, String name) throws  SQLException {

        int signUp = usersDao.signUp(id,pw,name);
        System.out.println("테스트테스트 "+signUp);
        if(signUp==1){

        return true;}
        else {

            return false;
        }
    }


    public boolean duplicateCheck(String checkId) throws DuplicateException {

        System.out.println("testing"+checkId);
       boolean booleanCheckId= usersDao.duplicateCheck(checkId);
        System.out.println("testert"+booleanCheckId);

        if(booleanCheckId==true) {

            throw new DuplicateException("중복된 아이디입니다");

        }
        return true;
    };


}
