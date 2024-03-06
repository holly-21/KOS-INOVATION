package backend.service;

import backend.exception.DuplicateException;
import backend.exception.IncorrectInputException;
import backend.exception.SearchWrongException;
import backend.model.dao.UsersDao;
import backend.model.dao.UsersDaoImpl;
import backend.model.dto.UsersDto;
import backend.model.session.Session;
import backend.model.session.SessionSet;
import front.NonUserFront;
import front.SuccessView;

import java.sql.SQLException;

public class UsersService {
    UsersDao usersDao =new UsersDaoImpl();


    public void signUp(String id, String pw, String name) throws SQLException {

        int signUpResult = usersDao.signUp(id,pw,name);
        if(signUpResult==1) {
            SuccessView.messagePrint("회원가입을 축하드립니다.");
        }


    }
    public void duplicateCheck(String checkId) throws DuplicateException {

        System.out.println("testing"+checkId);
       boolean booleanCheckId= usersDao.duplicateCheck(checkId);
        System.out.println("testert"+booleanCheckId);

        if(booleanCheckId) {//true =Db에 존재함.
            throw new DuplicateException("존재하는 아이디입니다");


        }

    };

    public UsersDto login (String userId, String password) throws SQLException, IncorrectInputException {

        UsersDto usersDto = usersDao.login(userId,password);
        if(usersDto==null){
            throw new IncorrectInputException("잘못 입력하셨습니다.");
        }

        Session session = new Session(userId);
        SessionSet sessionSet = SessionSet.getInstance();
        sessionSet.add(session);

        return usersDto;

    }

    public int searchByUserId(String userId) throws SQLException,SearchWrongException {
        int userNum = usersDao.searchByUserId(userId);
        if(userNum==-1) throw new SearchWrongException("로그인 정보가 없습니다.");
        return userNum;
    }



}
