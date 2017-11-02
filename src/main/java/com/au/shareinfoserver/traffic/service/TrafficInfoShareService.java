package com.au.shareinfoserver.traffic.service;

import com.au.shareinfoserver.dao.Message;
import com.au.shareinfoserver.dao.MessageRepository;
import com.au.shareinfoserver.dao.TrafficInfo;
import com.au.shareinfoserver.dao.TrafficInfoRepository;
import com.au.shareinfoserver.traffic.convertor.TrafficInfoConvertor;
import com.au.shareinfoserver.traffic.convertor.TrafficMessageConvertor;
import com.au.shareinfoserver.traffic.model.Location;
import com.au.shareinfoserver.traffic.model.ShareBusInfoResponse;
import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.utils.JsonUtil;
import com.au.shareinfoserver.utils.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficInfoShareService {
    private static final Integer MIN_DISTANCE = 50;
    private static final Integer ONE = 1;
    @Autowired
    TrafficInfoRepository trafficInfoRepository;
    @Autowired
    TrafficInfoConvertor trafficInfoConvertor;
    @Autowired
    TrafficMessageConvertor trafficMessageConvertor;
    @Autowired
    MessageRepository messageRepository;

    public TrafficInfo saveTrafficInfo(ShareInfo shareInfo) {
        //ToDo Add more logic to analysis this share behaviour.
        TrafficInfo trafficInfo = updateTrafficInfo(shareInfo);
        if (trafficInfo == null)
            trafficInfo = createNewShareInfo(shareInfo);
        return trafficInfo;

    }

    public TrafficInfo updateTrafficInfo(ShareInfo shareInfo) {
        List<TrafficInfo> carInfos = trafficInfoRepository.findByCarNumber(shareInfo.getCarInfo().getCarNumber());
        Location shareLocation = shareInfo.getLocation();

        if (carInfos != null) {
            for (TrafficInfo carInfo :
                    carInfos) {
                if (updateTrafficInfoLocation(shareLocation, carInfo)) return carInfo;
            }
        }
        return null;
    }


    private TrafficInfo createNewShareInfo(ShareInfo shareInfo) {
        shareInfo.setNumOfPeople(ONE);
        TrafficInfo trafficInfo = trafficInfoConvertor.convertShareInfoToCarInfo(shareInfo);
        trafficInfoRepository.save(trafficInfo);
        return trafficInfo;
    }

    private boolean updateTrafficInfoLocation(Location shareLocation, TrafficInfo carInfo) {
        Location storedLocation = JsonUtil.parseJson(carInfo.getLocation(), Location.class);
        if (LocationUtil.distance(shareLocation.getLatitude(), shareLocation.getLongitude(),
                storedLocation.getLatitude(), storedLocation.getLongitude()) < MIN_DISTANCE) {
            trafficInfoRepository.updateTrafficInfo(carInfo.getNumOfPeople() + ONE, carInfo.getLocation(), carInfo.getUuid());
            return true;
        }
        return false;
    }


    public ShareBusInfoResponse handleShareInfo(String phoneNum, ShareInfo shareInfo) {
        TrafficInfo trafficInfo = saveTrafficInfo(shareInfo);
        Message message = trafficMessageConvertor.covertTrafficInfoToMessage(phoneNum, trafficInfo);
        messageRepository.save(message);

        return new ShareBusInfoResponse(message.getInfoUuid());
    }

    public void removeInfo(String phoneNum, String messageUuid) {
        TrafficInfo trafficInfo = trafficInfoRepository.findByUuid(messageUuid);
        trafficInfoRepository.updateNumberOfPeople(trafficInfo.getNumOfPeople() - 1, messageUuid);
        messageRepository.deleteByPhoneNumAndInfoUuid(phoneNum, messageUuid);
    }
}
