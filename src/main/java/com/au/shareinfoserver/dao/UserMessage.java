package com.au.shareinfoserver.dao;

import javax.persistence.*;

@Entity
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String infoUuid;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getInfoUuid() {
        return infoUuid;
    }

    public void setInfoUuid(String infoUuid) {
        this.infoUuid = infoUuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
