package com.au.shareinfoserver.traffic.convertor;

import com.au.shareinfoserver.dao.Message;
import com.au.shareinfoserver.dao.TrafficInfo;
import com.au.shareinfoserver.model.InfoType;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
public class TrafficMessageConvertor {
    public Message covertTrafficInfoToMessage(String phoneNum, TrafficInfo trafficInfo) {
        Message message = new Message();
        message.setInfoUuid(trafficInfo.getUuid());
        message.setPhoneNum(phoneNum);
        message.setType(InfoType.TRAFFIC_INFO.getType());
        return message;
    }

}
