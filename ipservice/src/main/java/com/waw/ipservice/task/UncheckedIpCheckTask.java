package com.waw.ipservice.task;

import com.waw.ipservice.config.IpCheckConfig;
import com.waw.ipservice.dao.CheckedIpDao;
import com.waw.ipservice.dao.NotUseIpDao;
import com.waw.ipservice.dao.UncheckedIpDao;
import com.waw.ipservice.domain.CheckedIp;
import com.waw.ipservice.domain.NotUseIp;
import com.waw.ipservice.domain.UncheckedIp;
import com.waw.ipservice.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author flyfish
 * @date 2020-08-21 15:10:51
 * @description
 */
@Component
public class UncheckedIpCheckTask {
    @Autowired
    UncheckedIpDao uncheckedIpDao;
    @Autowired
    CheckedIpDao checkedIpDao;
    @Autowired
    NotUseIpDao notUseIpDao;
    @Autowired
    IpCheckConfig ipCheckConfig;
    static List<UncheckedIp> ipList;
    static int nowIndex = 0;
//    private ReentrantLock reentrantLock = new ReentrantLock(true);//公平锁

    @Async("uncheckedIpPool")
    public void check() {
        if (ipList == null) {
            ipList = uncheckedIpDao.findAll();
        }
        int checkResult = -1;
        CheckedIp checkedIp = null;
        NotUseIp notUseIp = null;
        UncheckedIp uncheckedIp = null;
        String ip = null;
        Long id = null;
        String website = null;
        while (ipList != null && ipList.size() > 0 && nowIndex < ipList.size()) {
            uncheckedIp = ipList.get(nowIndex);//在多线程编程中,对公共资源尽量减少占用,以提高并发能力
            nowIndex++;
            ip = uncheckedIp.getIp();
            id = uncheckedIp.getId();
            website = uncheckedIp.getWebsite();

            checkResult = IpUtil.getIpProtocol(ip);
            if (checkResult >= 0) {
                checkedIp = new CheckedIp(ip, checkResult, website);
                checkedIpDao.save(checkedIp);
                uncheckedIpDao.deleteById(id);
            } else {
                if (uncheckedIp.getCheckedTimes() + 1 > ipCheckConfig.getTimes()) {
                    notUseIp = new NotUseIp(ip, website);
                    notUseIpDao.save(notUseIp);
                    uncheckedIpDao.deleteById(id);
                } else {
                    uncheckedIpDao.updateCheckTimeById(id);
                }
            }

        }
        ipList = null;
        nowIndex = 0;
    }
}
