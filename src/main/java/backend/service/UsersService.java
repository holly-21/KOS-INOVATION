package backend.service;

import backend.model.dao.UsersDao;
import backend.model.dao.UsersDaoImpl;

import java.sql.SQLException;

public class UsersService {

    UsersDao usersDao =new UsersDaoImpl();


    public boolean signUp(String id, String pw, String name) throws  SQLException {

        int signUp = usersDao.signUp(id,pw,name);
        System.out.println(signUp);




        System.out.println(id+pw);




        return true;
    }


    public boolean duplicateCheck(String checkId) throws SQLException {

        System.out.println("testing"+checkId);
       boolean booleanCheckId= usersDao.duplicateCheck(checkId);
        System.out.println("testert"+booleanCheckId);

        if(booleanCheckId==false){
            // 중복되는아이디 없을시 반환되는 메세지.
            return false;

        }
        return true;
    };


}
