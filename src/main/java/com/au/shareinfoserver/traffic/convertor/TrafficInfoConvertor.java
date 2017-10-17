package com.au.shareinfoserver.traffic.convertor;

import com.au.shareinfoserver.dao.TrafficInfo;
import com.au.shareinfoserver.traffic.model.CarInfo;
import com.au.shareinfoserver.traffic.model.Location;
import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.utils.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.au.shareinfoserver.utils.JsonUtil.toJson;

@Component
public class TrafficInfoConvertor {
    public TrafficInfo convertShareInfoToCarInfo(ShareInfo shareInfo) {
        TrafficInfo trafficInfo = new TrafficInfo();
        trafficInfo.setLocation(toJson(shareInfo.getLocation()));
        trafficInfo.setCarNumber(shareInfo.getCarInfo().getCarNumber());
        trafficInfo.setCity(shareInfo.getCarInfo().getCity());
        trafficInfo.setProvince(shareInfo.getCarInfo().getProvince());
        return trafficInfo;
    }


    public List<ShareInfo> convertTrafficInfoListToCarInfoList(List<TrafficInfo> trafficInfoList) {
        ArrayList shareInfoLists = new ArrayList();
        for (TrafficInfo trafficInfo :
                trafficInfoList) {
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
