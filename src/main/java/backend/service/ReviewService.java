package backend.service;

import backend.exception.DMLException;
import backend.exception.SearchWrongException;
import backend.model.dao.*;
import backend.model.dto.ReviewDto;

import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    ReviewDao reviewDao = new ReviewDaoImpl();
    ChargeStationDao chargeStationDao = new ChargeStationDaoImpl();
    RecieptDao recieptDao = new RecieptDaoImpl();
    UsersDao usersDao = new UsersDaoImpl();

    //리뷰 작성
    public void writeReviewService(int userNum, String stationName, String content, int star) throws SQLException, SearchWrongException, DMLException {

        //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
        int stationId = chargeStationDao.searchByStationName(stationName);
        if(stationId==0) throw new SearchWrongException("해당 충전소를 찾을 수 없습니다.");
        String userId = usersDao.searchByUserNum(userNum);
//        String userId = "test"; //TEST
        if(userId==null) throw new SearchWrongException("사용자 세션을 찾을 수 없습니다.");

        //RecieptDaoImpl에서 사용자아이디와 충전소 이름으로 결제내역Id 찾기
        int receiptId = recieptDao.SearchReceipt(userNum, stationId);
//        int receiptId = recieptDao.SearchReceipt(2, 1); //TEST
        if(receiptId==0) throw new SQLException("결제 내역이 존재하지 않습니다.");

        int result = reviewDao.writeReview(userNum, stationId, content, star);
        if(result==0) throw new DMLException("리뷰 작성을 실패하였습니다.");
    }

    public Object[] getGroup(String group, String stationName, int userNum) throws SQLException,SearchWrongException{
        Object[] result = new Object[2];
        String g="";
        int id =-1;
        if(group.equals("station")){
            //ChargeStationDaoImpl에서 충전소 이름으로 충전소Id 찾기
            id = chargeStationDao.searchByStationName(stationName);
            if(id==0) throw new SearchWrongException("해당 충전소를 찾을 수 없습니다.");
            g = "stationId";
        } else if(group.equals("users")){
            //회원번호로 충전소 찾기
            g="userNum";
            id=userNum;
        }
        result[0] = id;
        result[1] = g;
        return result;
    }

    // 리뷰 조회
    public List<ReviewDto> searchReviewService(String group, String stationName, int userNum) throws SQLException,SearchWrongException {
        Object[] groupInfo = getGroup(group, stationName, userNum);
        int id = (int) groupInfo[0];
        String g = (String) groupInfo[1];

        List<ReviewDto> list = reviewDao.searchReview(id, g);
        if(list==null || list.isEmpty()) throw new SearchWrongException("작성된 리뷰가 없습니다.");
        return list;
    }

    //리뷰 정렬
    public List<ReviewDto> sortReviewByStandard(String group, String stationName, String standard, int userNum, int order) throws SQLException {
        Object[] groupInfo = getGroup(group, stationName, userNum);
        int id = (int) groupInfo[0];
        String g = (String) groupInfo[1];
        return reviewDao.sortReviewByStandard(g,id,standard,userNum,order);
    }
    public List<ReviewDto> sortReviewByString(String group, String stationName, int userNum, String order) throws SQLException {
        Object[] groupInfo = getGroup(group, stationName, userNum);
        int id = (int) groupInfo[0];
        String g = (String) groupInfo[1];

        return reviewDao.sortReviewByString(g,id,order);
    }
}
