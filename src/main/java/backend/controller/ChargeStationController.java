package backend.controller;

import backend.model.dto.ChargeStationDto;
import backend.service.ChargeStationService;
import front.FailView;
import front.locFront;

import java.util.List;

public class ChargeStationController {
    static ChargeStationService chargeStationService = new ChargeStationService();
    static locFront locFront2 =  new locFront();

    /**
     * 충전소 위치 조회
     */
    public static void searchStationController(String location) {
        try {
            int count=1;
            List<ChargeStationDto> list=chargeStationService.searchStationService(location);
            for(ChargeStationDto chargeStationDto : list) {
                System.out.println(count+": 충전소명: "+chargeStationDto.getStationName()+" 충전소 / 업체명: "+chargeStationDto.getOrganization()+
                        " / 위치: "+chargeStationDto.getLocation());
                count++;
            }
            System.out.println();

        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
            locFront2.locFront();
        }

    }

    /**
     * 충전소 이름 조회
     */
    public static void searchByStationName(String stationName){
        try{
            List<ChargeStationDto> list= chargeStationService.searchByStationName(stationName);
            int count=1;
            for(ChargeStationDto chargeStationDto : list) {
                System.out.println(count+": 충전소명: "+chargeStationDto.getStationName()+" 충전소 / 업체명: "+chargeStationDto.getOrganization()+
                        " / 위치: "+chargeStationDto.getLocation());
                count++;
            }
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
            locFront2.locFront();
        }
    }


    public static void searchByOraganizationName(String organizationName) {
        try{
            List<ChargeStationDto> list= chargeStationService.searchByOraganizationName(organizationName);
            int count=1;
            for(ChargeStationDto chargeStationDto : list) {
                System.out.println(count+": 충전소명: "+chargeStationDto.getStationName()+" 충전소 / 업체명: "+chargeStationDto.getOrganization()+
                        " / 위치: "+chargeStationDto.getLocation());
                count++;
            }
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
            locFront2.locFront();
        }
    }
}
