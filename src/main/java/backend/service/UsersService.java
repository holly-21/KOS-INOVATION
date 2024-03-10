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
import front.UserFront;

import java.sql.SQLException;

public class UsersService {
    UsersDao usersDao = new UsersDaoImpl();

    NonUserFront nonUserFront = new NonUserFront();

    public void signUp(String name, String id, String pw) throws SQLException {
        int signUpResult = usersDao.signUp(name, id, pw);
        if (signUpResult == 1) {
            SuccessView.messagePrint("회원가입을 축하드립니다.");
            nonUserFront.nonUserFrontview();
        }


    }

    public void duplicateCheck(String checkId) throws DuplicateException {

        boolean booleanCheckId = usersDao.duplicateCheck(checkId);

        if (booleanCheckId) {//true =Db에 존재함.
            throw new DuplicateException("존재하는 아이디입니다");


        }

    }

    ;

    public UsersDto login(String userId, String password) throws SQLException, IncorrectInputException {

        UsersDto usersDto = usersDao.login(userId, password);
        if (usersDto == null) {
            throw new IncorrectInputException("잘못 입력하셨습니다.");
        }

        Session session = new Session(userId);
        SessionSet sessionSet = SessionSet.getInstance();
        sessionSet.add(session);

        return usersDto;

    }

    public int searchByUserId(String userId) throws SQLException, SearchWrongException {
        int userNum = usersDao.searchByUserId(userId);
        if (userNum == -1)
            throw new SearchWrongException("로그인 정보가 없습니다.");
        return userNum;
    }

    public int searcBalanceByUserId(String userId) throws SQLException {
        int balance = usersDao.searchBalanceByUserId(userId);
        return balance;
    }

    public void chargeCoin(String userId, int balance, int coinQuantity) throws SQLException {
        int usersDto = usersDao.buyCoin(userId, balance, coinQuantity);
        if (usersDto == 1) {
            SuccessView.messagePrint("정상 충전 되었습니다");
        } else {
            throw new IncorrectInputException("잘못 입력하셨습니다.");
        }


    }


}
