package backend.model.dao;

import backend.exception.DMLException;
import backend.exception.SearchWrongException;
import backend.model.dto.UsersDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {
    @Override
    public int signUp(String userId, String userName, String password) throws DMLException {
        Connection con= null;
        PreparedStatement ps = null;
        String sql = "insert into USERS values (USERS_SEQ.nextval, ? , ?, default, ?, sysdate )";
        int result = 0;

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,userId);
            ps.setString(2,userName);
            ps.setString(3,password);

            ps.executeUpdate();

        }
        catch (SQLException e){

        }
        finally {
            DBManager.releaseConnection(con,ps);
        }
        return result;
    }

    @Override
    public boolean duplicateCheck(String userId) throws SearchWrongException {
        Connection con =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql= "select userId from users where userId=?";
        try {
            con=DBManager.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1, userId);
            rs=ps.executeQuery();

            if(rs.next()){
                return true;

            }else {
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new SearchWrongException("DB에 문제가 있습니다 다시 진행해주십시오.");
        }
        finally {
            DBManager.DbClose(con,ps,rs);
        }

    }

    @Override
    public UsersDto login(String userId, String password) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        UsersDto usersDto= null;

        String  sql = "select * from users where user_id=? and user_pwd=?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, password);
        }catch (SearchWrongException s){
            throw new SearchWrongException("오류발생. 다시 진행해주세요.");

        }finally {
            DBManager.DbClose(con,ps,rs);
        }

        return null;
    }




    @Override
    public int buyCoin(String userId, int balance, int coinQuantity) {
        return 0;
    }

    @Override
    public String searchByUserNum(int userNum) {
        return null;
    }


}
