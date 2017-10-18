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
public interface TrafficInfoRepository extends JpaRepository<TrafficInfo, Long> {
    List<TrafficInfo> findByCarNumber(String cardNumber);

    @Modifying(clearAutomatically = true)
    @Query("update TrafficInfo info set info.numOfPeople = :numOfPeople where  info.id = :id")
    void updateNumberOfPeople(@Param("numOfPeople") Integer numOfPeople, @Param("id") Integer id);


    @Query("select info from TrafficInfo info where info.carNumber = :carNumber")
    List<TrafficInfo> getTrafficInfoByCarNumber(@Param("carNumber") String carNumber);
}
