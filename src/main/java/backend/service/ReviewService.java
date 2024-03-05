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
    public void writeReviewService(int userNum, String stationName, String content, int star) throws SQLException {

        //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
        int stationId = chargeStationDao.searchByStationName(stationName);
//        String userId = usersDao.searchByUserNum(userNum);
        String userId = "test"; //TEST

        //RecieptDaoImpl에서 사용자아이디와 충전소 이름으로 결제내역Id 찾기
        int receiptId = recieptDao.SearchReceipt(userNum, stationId);
        if(receiptId==0) throw new SQLException("결제 내역이 존재하지 않습니다.");

        int result = reviewDao.writeReview(userNum, stationId, content, star);
        System.out.println(result);

        if(result==0) throw new SQLException("리뷰 작성을 실패하였습니다.");
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
    public List<ReviewDto> searchReviewService(String group, String Name, int userNum) throws SQLException {
        int id = -1;
        List<ReviewDto> list = new ArrayList<>();
        if(group.equals("station")){
            //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
            id = chargeStationDao.searchByStationName(Name);
            list = reviewDao.searchReview(id,"stationId");
        } else if(group.equals("users")){
            //로그인 세션으로 사용자 Id 찾기
            id = userNum;
            list = reviewDao.searchReview(id,"usersNum");
        }

        if(list==null || list.isEmpty()) throw new SQLException("작성된 리뷰가 없습니다.");
        return list;
    }


    //리뷰 정렬
    public List<ReviewDto> sortReviewByStandard(int standard, int userNum) throws SQLException {
        List<ReviewDto> list = reviewDao.sortReviewByStandard(standard,userNum);
        return list;
    }
}
