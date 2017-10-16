package com.au.shareinfoserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {
    List<CarInfo> findByCarNumber(String cardNumber);

    @Modifying(clearAutomatically = true)
    @Query("update CarInfo cardInfo set cardInfo.numOfPeople = :numOfPeople where  cardInfo.id = :id")
    void updateNumberOfPeople(@Param("numOfPeople") Integer numOfPeople, @Param("id") Integer id);


}
