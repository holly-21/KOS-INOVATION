package backend.model.dto;

import java.util.Date;

public class UsersDto {
private int userNum;
private String userId;
private String userName;
private int balance;
private String password;
private Date regDate;

public UsersDto(){};

    public UsersDto(int userNum, String userId, String userName, int balance, String password, Date regDate) {
        this.userNum = userNum;
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;
        this.password = password;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "UsersDto{" +
                "userNum=" + userNum +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
