package backend.model.dao;

import backend.model.dto.UsersDto;

public class UsersDaoImpl implements UsersDao {
    @Override
    public void signUp(String userId, String userName, String password) {

    }

    @Override
    public boolean duplicateCheck(String userId) {
        return false;
    }

    @Override
    public UsersDto login(String userId, String password) {
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
