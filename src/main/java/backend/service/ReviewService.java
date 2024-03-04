package backend.service;

import backend.model.dao.*;
import backend.model.dto.ReviewDto;

import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    ReviewDao reviewDao = new ReviewDaoImpl();
    ChargeStationDao chargeStationDao = new ChargeStationDaoImpl();
    RecieptDao recieptDao = new RecieptDaoImpl();
    UsersDao usersDao = new UsersDaoImpl();
//
    //리뷰 작성
    public int writeReviewService(int userNum, String stationName, String content, int star) throws SQLException {

        //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
        int stationId = chargeStationDao.searchByStationName(stationName);
        String userId = usersDao.searchByUserNum(userNum);

        //RecieptDaoImpl에서 사용자아이디와 충전소 이름으로 결제내역Id 찾기
        int receiptId = recieptDao.SearchReceipt(userId, stationName);
        if(receiptId==0) throw new SQLException("결제 내역이 존재하지 않습니다.");

        int result = reviewDao.writeReview(userNum, stationId, content, star);

        if(result==0) throw new SQLException("주문하기가 실패하였습니다.");
        return result;
    }

    public List<ReviewDto> searchReviewByStation(String stationName) {
        return null;
    }

    public List<ReviewDto> searchReviewByUser(String userId) {
        return null;
    }

    public List<ReviewDto> sortReviewByStandard(int standard, List<ReviewDto> review) {
        return null;
    }
}
