package backend.model.dao;

import backend.exception.DMLException;
import backend.exception.IncorrectInputException;
import backend.exception.SearchWrongException;
import backend.model.dto.UsersDto;
import common.DBManager;
import front.FailView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {
    @Override
    public int signUp(String userName, String userId, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into USERS values (USERS_SEQ.nextval, ? , ?, default, ?, sysdate )";
        int result = 0;

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, userName);
            ps.setString(3, password);

            result = ps.executeUpdate();


        } finally {
            DBManager.releaseConnection(con, ps);

        }
        return result;
    }

    @Override
    public boolean duplicateCheck(String userId) throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select userId from users where userId=?";
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new SearchWrongException("DB에 문제가 있습니다 다시 진행해주십시오.");
        } finally {
            DBManager.DbClose(con, ps, rs);
        }

    }

    @Override
    public UsersDto login(String userId, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsersDto usersDto = null;

        String sql = "select * from users where userid=? and PASSWORD=?";

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                usersDto =
                        new UsersDto(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6)

                        );
            }

        } catch (SearchWrongException s) {
            throw new SearchWrongException("오류발생. 다시 진행해주세요.");

        } finally {
            DBManager.DbClose(con, ps, rs);
        }

        return usersDto;
    }


    @Override
    public int buyCoin(String userId, int balance, int coinQuantity) throws SQLException {
        Connection con = null;
        PreparedStatement ps= null;
        String sql= "update users set balance= balance+?  where userId=? ";
        int result = 0;
        try{
            con= DBManager.getConnection();
            ps= con.prepareStatement(sql);
            ps.setInt(1,coinQuantity);
            ps.setString(2, userId);
            result = ps.executeUpdate();
        }
        finally {
            DBManager.releaseConnection(con,ps);
        }

        return result;
    }

    @Override
    public int searchByUserId(String userId) throws SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="select userNum from USERS where userId=?";
        int userNum=-1;

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,userId);
            rs = ps.executeQuery();
            if(rs.next()) userNum = rs.getInt(1);
        }finally {
            DBManager.DbClose(con,ps,rs);
        }
        return userNum;
    }


}
