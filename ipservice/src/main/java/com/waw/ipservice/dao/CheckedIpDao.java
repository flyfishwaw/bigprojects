package com.waw.ipservice.dao;

import com.waw.ipservice.domain.CheckedIp;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author flyfish
 * @date 2020-08-20 16:26:54
 * @description
 */
@Repository
public interface CheckedIpDao extends JpaRepository<CheckedIp, Long> {
    @Transactional
    @Modifying
    @Query(value = "update checked_ip set check_time=convert(char(20),getdate(),120 ) where id=?1", nativeQuery = true)
    public int updateCheckTimeById(long id);
}
