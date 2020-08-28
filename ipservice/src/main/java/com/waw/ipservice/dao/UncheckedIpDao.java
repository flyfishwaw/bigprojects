package com.waw.ipservice.dao;

import com.waw.ipservice.domain.UncheckedIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author flyfish
 * @date 2020-08-20 16:26:54
 * @description
 */
@Repository
@Component
public interface UncheckedIpDao extends JpaRepository<UncheckedIp, Long> {
    @Transactional
    @Modifying
    @Query(value = "update unchecked_ip set check_time=now(),checked_times=checked_times+1 where id=?1", nativeQuery = true)
    public int updateCheckTimeById(long id);
}
