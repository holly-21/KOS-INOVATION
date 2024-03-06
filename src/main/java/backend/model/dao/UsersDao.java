package backend.model.dao;

import backend.exception.DMLException;
import backend.exception.SearchWrongException;
import backend.model.dto.UsersDto;

import java.sql.SQLException;

public interface UsersDao {
    /**
     * 회원가입
     * (사용자 아이디, 사용자 이름, 사용자 비밀번호)
     */
    int signUp(String userId, String userName, String password) throws SQLException;

    /**
     * 이름 중복체크
     * @return 중복 여부
     * (사용자 아이디)
     */
    boolean duplicateCheck(String userId) throws SearchWrongException;

    /**
     * 로그인
     * @return 사용자 정보
     * (사용자 아이디, 사용자 비밀번호)
     */
    UsersDto login(String userId, String password) throws SQLException;

    /**
     * 코인 충전
     * @return 잔액
     * (사용자 아이디, 잔액, 코인 충전량)
     */
    int buyCoin(String userId, int balance, int coinQuantity) throws  SQLException;

    /**
     * 회원번호로 사용자 아이디 찾기
     * @return 사용자 아이디
     * (userNum)
     */
    int searchByUserId(String userId) throws SQLException;
}
