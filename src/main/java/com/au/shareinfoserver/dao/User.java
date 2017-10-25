package com.au.shareinfoserver.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"phoneNum"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String passWord;

    private String emailAddress;
    private Integer credit = 0;
    private Integer cash = 0;
    private Set<UserMessage> userMessages;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    @OneToMany
    @JoinTable(name = "User_UserMessage", joinColumns = @JoinColumn(name = "User_phoneNum"), inverseJoinColumns = @JoinColumn(name = "UserMessage_phoneNum"))
    public Set<UserMessage> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(Set<UserMessage> userMessages) {
        this.userMessages = userMessages;
    }
}
