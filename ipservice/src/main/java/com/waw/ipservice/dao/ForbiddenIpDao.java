package com.waw.ipservice.dao;

import com.waw.ipservice.domain.ForbiddenIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flyfish
 * @date 2020-08-20 16:26:54
 * @description
 */
@Repository
public interface ForbiddenIpDao extends JpaRepository<ForbiddenIp, Long> {
}
