package com.au.shareinfoserver.traffic.convertor;

import com.au.shareinfoserver.dao.TrafficInfo;
import com.au.shareinfoserver.dao.TrafficInfoRepository;
import com.au.shareinfoserver.traffic.model.CarInfo;
import com.au.shareinfoserver.traffic.model.Location;
import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.au.shareinfoserver.utils.JsonUtil.toJson;

@Component
public class TrafficInfoConvertor {
    @Autowired
    TrafficInfoRepository carInfoRepository;

    public TrafficInfo convertShareInfoToCarInfo(ShareInfo shareInfo) {
        TrafficInfo trafficInfo = new TrafficInfo();
        trafficInfo.setLocation(toJson(shareInfo.getLocation()));
        trafficInfo.setCarNumber(shareInfo.getCarInfo().getCarNumber());
        trafficInfo.setCity(shareInfo.getCarInfo().getCity());
        trafficInfo.setProvince(shareInfo.getCarInfo().getProvince());
        trafficInfo.setUuid(UUID.randomUUID().toString());
        return trafficInfo;
    }


    public List<ShareInfo> convertTrafficInfoListToCarInfoList(String carNumber) {
        ArrayList shareInfoLists = new ArrayList();
        if (carNumber == null || carNumber.isEmpty())
            return shareInfoLists;

        List<TrafficInfo> trafficInfos = carInfoRepository.findByCarNumber(carNumber);
        for (TrafficInfo trafficInfo :
                trafficInfos) {
            shareInfoLists.add(convertTrafficInfoToCarInfo(trafficInfo));
        }
        return shareInfoLists;
    }

    public ShareInfo convertTrafficInfoToCarInfo(TrafficInfo trafficInfo) {
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.setCarInfo(getCarInfo(trafficInfo));
        shareInfo.setLocation(JsonUtil.parseJson(trafficInfo.getLocation(), Location.class));
        shareInfo.setNumOfPeople(trafficInfo.getNumOfPeople());
        return shareInfo;
    }

    private CarInfo getCarInfo(TrafficInfo trafficInfo) {
        CarInfo carInfo = new CarInfo();
        carInfo.setCarNumber(trafficInfo.getCarNumber());
        carInfo.setProvince(trafficInfo.getProvince());
        carInfo.setCity(trafficInfo.getCity());
        return carInfo;
    }
}
