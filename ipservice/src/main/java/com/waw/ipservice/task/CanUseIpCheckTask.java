package com.waw.ipservice.task;

import com.waw.ipservice.domain.Ip;
import com.waw.ipservice.service.IpServie;
import com.waw.ipservice.utils.DateUtil;
import com.waw.ipservice.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author flyfish
 * @date 2020-08-21 15:10:51
 * @description
 */
@Component
public class CanUseIpCheckTask {
    @Autowired
    IpServie ipServie;

    @Async("checkedIpPool")
    public void check() {
        System.out.println(Thread.currentThread().getName());
        List<Ip> ipList = ipServie.getIp4Check(1, 100);
        int checkResult = -1;

        for (Ip ip : ipList) {

            try {
                checkResult = IpUtil.getIpProtocol(ip.getIp());
                ip.setLastCheckTime(DateUtil.formatDate(new Date()));
                if (checkResult >= 0) {
                    ip.setProtocol(checkResult);
                } else {
                    ip.setCanUse(false);
                    ip.setCheckTimes(1);
                }
                ipServie.save(ip);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                continue;
            }

        }
    }
}
