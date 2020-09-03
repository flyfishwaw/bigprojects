package com.waw.ipservice.task;

import com.waw.ipservice.dao.CheckedIpDao;
import com.waw.ipservice.dao.UncheckedIpDao;
import com.waw.ipservice.domain.CheckedIp;
import com.waw.ipservice.domain.UncheckedIp;
import com.waw.ipservice.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author flyfish
 * @date 2020-08-21 15:10:51
 * @description
 */
@Component
public class CheckedIpCheckTask {
    @Autowired
    UncheckedIpDao uncheckedIpDao;
    @Autowired
    CheckedIpDao checkedIpDao;
    private static List<CheckedIp> ipList;
    private static int nowIndex = 0;

    @Async("checkedIpPool")
    public void check() {
        System.out.println(Thread.currentThread().getName());
        if (ipList == null) {
            ipList = checkedIpDao.findAll();
        }
        int checkResult = -1;
        UncheckedIp uncheckedIp = null;
        String ip = null;
        Long id = null;
        CheckedIp checkedIp = null;
        while (ipList != null && ipList.size() > 0 && nowIndex < ipList.size()) {
            checkedIp = ipList.get(nowIndex);
            nowIndex++;
            id = checkedIp.getId();
            ip = checkedIp.getIp();
            try {
                checkResult = IpUtil.getIpProtocol(ip);
                if (checkResult >= 0) {
                    checkedIpDao.updateCheckTimeById(id);
                } else {
                    uncheckedIp = new UncheckedIp(ip, checkedIp.getWebsite());
                    uncheckedIpDao.save(uncheckedIp);
                    checkedIpDao.deleteById(checkedIp.getId());
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
                continue;
            }

        }
        ipList = null;
        nowIndex = 0;
    }
}
