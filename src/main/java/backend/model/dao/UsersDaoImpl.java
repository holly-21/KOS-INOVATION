package backend.model.dao;

import backend.model.dto.UsersDto;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {
    @Override
    public int signUp(String userId, String userName, String password) throws SQLException {
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
        finally {
            DBManager.releaseConnection(con,ps);
        }
            return result;
    }

    @Override
    public boolean duplicateCheck(String userId) throws SQLException {
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
                        }

        }
        finally {
        DBManager.DbClose(con,ps,rs);
        }


        return false;
    }

    @Override
    public UsersDto login(String userId, String password) throws SQLException {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs =null;
    UsersDto usersDto= null;

    String  sql = "select * from Customer where user_id=? and user_pwd=?";

        try {
            con=DBManager.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1,userId);
            ps.setString(2,password);

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
