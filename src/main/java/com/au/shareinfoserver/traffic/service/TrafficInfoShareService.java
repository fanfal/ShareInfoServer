package com.au.shareinfoserver.traffic.service;

import com.au.shareinfoserver.dao.TrafficInfo;
import com.au.shareinfoserver.dao.TrafficInfoRepository;
import com.au.shareinfoserver.traffic.convertor.TrafficInfoConvertor;
import com.au.shareinfoserver.traffic.model.Location;
import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.utils.JsonUtil;
import com.au.shareinfoserver.utils.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficInfoShareService {
    private static final Integer MIN_DISTANCE = 50;
    private static final Integer ONE = 1;
    @Autowired
    TrafficInfoRepository carInfoRepository;
    @Autowired
    TrafficInfoConvertor trafficInfoConvertor;

    public ResponseEntity saveOrUpdateInfo(ShareInfo shareInfo) {
        List<TrafficInfo> carInfos = carInfoRepository.findByCarNumber(shareInfo.getCarInfo().getCarNumber());
        Location shareLocation = shareInfo.getLocation();

        if (carInfos != null) {
            for (TrafficInfo carInfo :
                    carInfos) {
                if (updateShareInfo(shareLocation, carInfo)) return ResponseEntity.ok().build();
            }

        }
        createNewShareInfo(shareInfo);
        return ResponseEntity.ok().build();

    }

    private void createNewShareInfo(ShareInfo shareInfo) {
        shareInfo.setNumOfPeople(ONE);
        carInfoRepository.save(trafficInfoConvertor.convertShareInfoToCarInfo(shareInfo));
    }

    private boolean updateShareInfo(Location shareLocation, TrafficInfo carInfo) {
        Location storedLocation = JsonUtil.parseJson(carInfo.getLocation(), Location.class);
        if (LocationUtil.distance(shareLocation.getLatitude(), shareLocation.getLongitude(),
                storedLocation.getLatitude(), storedLocation.getLongitude()) < MIN_DISTANCE) {
            carInfoRepository.updateTrafficInfo(carInfo.getNumOfPeople() + ONE, carInfo.getLocation(), carInfo.getUuid());
            return true;
        }
        return false;
    }


    public ResponseEntity handleShareInfo(ShareInfo shareInfo) {
        if (shareInfo.getAboard()) {
            saveOrUpdateInfo(shareInfo);
        } else {
            //To Do decrease number of people.
        }
        return ResponseEntity.ok().build();
    }
}
