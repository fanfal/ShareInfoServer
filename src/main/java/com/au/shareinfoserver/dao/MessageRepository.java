package com.au.shareinfoserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteByPhoneNumAndInfoUuid(String phoneNum, String infoUuid);
}
