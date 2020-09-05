package com.waw.ipservice.dao;

import com.waw.ipservice.domain.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author flyfish
 * @date 2020-09-03 17:44:01
 * @description
 */
@Repository
public interface IpDao extends JpaRepository<Ip, Integer> {
    /**
     * 验证可用的ip,取验证时间最早的进行验证
     *
     * @param cnt
     * @return
     */
    //top后面不允许直接使用占位符,这里先写死，等后续再解决？？？？？？？
    @Query(value = "select top 100 * from ip where can_use=:canUse and checking=0 order by last_check_time ", nativeQuery = true)
    public List<Ip> find4Check(@Param("canUse") int canUse);

    @Query(value = "select top 100 * from ip where can_use=1 and (protocol=:protocol or protocol=2) order by last_check_time desc", nativeQuery = true)
    public List<Ip> findLastCanUse(@Param("protocol") int protocol);

    @Query(value = "select top 1 * from ip where can_use=1 and (protocol=:protocol or protocol=2) order by last_check_time desc", nativeQuery = true)
    public Ip findLastOneCanUse(@Param("protocol") int protocol);

    @Transactional
    @Query(value = "update Ip set checking=0 where checking=1", nativeQuery = true)
    @Modifying
    public void clearCheckingIp();
}
