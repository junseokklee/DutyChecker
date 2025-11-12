package com.dutyChecker.slackbot.repository;

import com.dutyChecker.slackbot.model.DutyInfo;
import com.dutyChecker.slackbot.model.DutyInfoTps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DutyInfoRepository extends JpaRepository<DutyInfo, Long> {
    @Query("SELECT d FROM DutyInfo d ORDER BY d.id.logDate DESC, d.id.logTime DESC")
    Optional<DutyInfo> findLatest();

    @Query("SELECT d FROM DutyInfo d ORDER BY d.id.logDate DESC, d.id.logTime DESC")
    List<DutyInfo> findTop1ByOrderByLogDateDescLogTimeDesc();

    @Query(value = """
    SELECT AVG(CAST(WEB_CON_COUNT AS FLOAT)) 
    FROM (
        SELECT WEB_CON_COUNT 
        FROM IF_WEB_CONNECTION 
        ORDER BY LOG_DATE DESC, LOG_TIME DESC 
        OFFSET 0 ROWS FETCH NEXT 12 ROWS ONLY
    ) AS recent_data
    """, nativeQuery = true)
    Double findAverageWebConCountOfRecent12();

//    @Query("SELECT d FROM DutyInfoTps d ORDER BY d.id.logDate DESC, d.id.logTime DESC")
//    List<DutyInfoTps> findTop1TPSByOrderByLogDateDescLogTimeDesc();

    @Query("SELECT t FROM DutyInfoTps t ORDER BY t.id.logDate DESC, t.id.logTime DESC")
    List<DutyInfoTps> findTop1TPSByOrderByLogDateDescLogTimeDesc();

}




