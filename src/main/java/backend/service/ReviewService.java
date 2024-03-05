package backend.service;

import backend.model.dao.*;
import backend.model.dto.ReviewDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    ReviewDao reviewDao = new ReviewDaoImpl();
    ChargeStationDao chargeStationDao = new ChargeStationDaoImpl();
    RecieptDao recieptDao = new RecieptDaoImpl();
    UsersDao usersDao = new UsersDaoImpl();

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

    // 충전소 별 리뷰 조회
    public List<ReviewDto> searchReviewStService(String stationName) throws SQLException {
        //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
        int stationId = chargeStationDao.searchByStationName(stationName);

        List<ReviewDto> list = reviewDao.searchReviewByStation(stationId);
        if(list==null || list.isEmpty()) throw new SQLException(stationName+" 충전소에 작성된 리뷰가 없습니다.");
        return list;
    }

    public List<ReviewDto> searchReviewByUser(String userId) {
        return null;
    }

    // 리뷰 조회
    public List<ReviewDto> searchReviewService(String group, String Name) throws SQLException {
        int id = -1;
        List<ReviewDto> list = new ArrayList<>();
        if(group=="station"){
            //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
            id = chargeStationDao.searchByStationName(Name);
            list = reviewDao.searchReview(id,"stationId");
        } else if(group=="users"){
            //로그인 세션으로 사용자 Id 찾기
//            id = usersDao;
//            list = reviewDao.searchReview(id,"usersNum");
        }

        if(list==null || list.isEmpty()) throw new SQLException("작성된 리뷰가 없습니다.");
        return list;
    }

    public List<ReviewDto> sortReviewByStandard(int standard, List<ReviewDto> review) {
        return null;
    }
}
