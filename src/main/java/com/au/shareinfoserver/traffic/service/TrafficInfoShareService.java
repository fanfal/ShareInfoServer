package com.au.shareinfoserver.traffic.service;

import com.au.shareinfoserver.dao.CarInfo;
import com.au.shareinfoserver.dao.CarInfoRepository;
import com.au.shareinfoserver.traffic.model.Location;
import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.au.shareinfoserver.utils.JsonUtil.toJson;

@Service
public class TrafficInfoShareService {
    private static final Integer MIN_DISTANCE = 100;
    @Autowired
    CarInfoRepository carInfoRepository;

    public ResponseEntity saveShareInfo(ShareInfo shareInfo) {
        List<CarInfo> carInfos = carInfoRepository.findByCarNumber(shareInfo.getCarInfo().getCarNumber());
        Location shareLocation = shareInfo.getLocation();

        if (carInfos != null) {
            for (CarInfo carInfo :
                    carInfos) {
                Location storedLocation = JsonUtil.parseJson(carInfo.getLocation(), Location.class);
                if (distance(shareLocation.getLatitude(), shareLocation.getLongitude(),
                        storedLocation.getLatitude(), storedLocation.getLongitude()) < MIN_DISTANCE) {
                    carInfoRepository.updateNumberOfPeople(carInfo.getNumOfPeople() + 1, carInfo.getId());
                    return ResponseEntity.ok().build();
                }
            }

        }
        return saveShareInfoToDataBase(shareInfo);

    }

    private ResponseEntity saveShareInfoToDataBase(ShareInfo shareInfo) {
        CarInfo carInfo = new CarInfo();
        carInfo.setLocation(toJson(shareInfo.getLocation()));
        carInfo.setCarNumber(shareInfo.getCarInfo().getCarNumber());
        carInfo.setCity(shareInfo.getCarInfo().getCity());
        carInfo.setProvince(shareInfo.getCarInfo().getProvince());
        carInfoRepository.save(carInfo);
        return ResponseEntity.ok().build();
    }


    private Integer distance(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) (earthRadius * c);
    }
}
