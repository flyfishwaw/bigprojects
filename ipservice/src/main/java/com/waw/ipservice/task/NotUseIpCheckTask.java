package com.waw.ipservice.task;

import com.waw.ipservice.config.IpCheckConfig;
import com.waw.ipservice.dao.CheckedIpDao;
import com.waw.ipservice.dao.NotUseIpDao;
import com.waw.ipservice.domain.CheckedIp;
import com.waw.ipservice.domain.NotUseIp;
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
public class NotUseIpCheckTask {

    @Autowired
    CheckedIpDao checkedIpDao;
    @Autowired
    NotUseIpDao notUseIpDao;
    @Autowired
    private static IpCheckConfig ipCheckConfig;
    private static List<NotUseIp> ipList;
    private static int nowIndex = 0;

    @Async("notUseIpPool")
    public void check() {
        if (ipList == null) {
            ipList = notUseIpDao.findAll();
        }
        int checkResult = -1;
        Long id = null;
        CheckedIp checkedIp = null;
        NotUseIp notUseIp = null;
        String ip = null;
        while (ipList != null && ipList.size() > 0 && nowIndex < ipList.size()) {
            notUseIp = ipList.get(nowIndex);
            nowIndex++;
            ip = notUseIp.getIp();
            id = notUseIp.getId();
            checkResult = IpUtil.getIpProtocol(ip);
            if (checkResult >= 0) {
                checkedIp = new CheckedIp(ip, checkResult, notUseIp.getWebsite());
                checkedIpDao.save(checkedIp);
                notUseIpDao.deleteById(id);
            } else {
                notUseIpDao.updateCheckTimeById(id);
            }

        }
        ipList = null;
        nowIndex = 0;

    }
}
