package backend.controller;

import backend.model.dto.ChargeStationDto;
import backend.model.dto.UsersDto;
import backend.service.ChargeStationService;
import front.FailView;
import front.SuccessView;

import java.sql.SQLException;
import java.util.List;

public class ChargeStationController {
    static ChargeStationService chargeStationService = new ChargeStationService();


    /**
     * 충전소 위치 조회
     */
    public static void searchStationController(String location) {
        try {
            List<ChargeStationDto> list=chargeStationService.searchStationService(location);
            for(ChargeStationDto lists : list) {
                System.out.println(lists);
            }
            System.out.println();

        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }

    }

    /**
     * 충전소 이름 조회
     */
    public static void searchByStationName(String stationName){
        try{
            chargeStationService.searchByStationName(stationName);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 충전소 위치 조회 결과
     */
    public static void printChargeStationLocationList(List<ChargeStationDto> list) {
        for(ChargeStationDto chargestationdto : list) {
            System.out.println(chargestationdto);
        }
        System.out.println();
    }
}
