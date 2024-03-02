package backend.model.dto;

import java.util.Date;

public class UsersDto {
private int userId;
private String userName;
private int balance;
private String password;
private Date regDate;

public UsersDto(){};

    public UsersDto(int userId, String userName, int balance, String password, Date regDate) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;
        this.password = password;
        this.regDate = regDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    @Override
    public String toString() {
        return "UsersDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
