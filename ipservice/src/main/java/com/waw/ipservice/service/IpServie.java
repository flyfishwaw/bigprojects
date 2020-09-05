package com.waw.ipservice.service;

import com.waw.ipservice.dao.IpDao;
import com.waw.ipservice.domain.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author flyfish
 * @date 2020-09-03 17:45:40
 * @description
 */
@Service
public class IpServie {
    @Autowired
    private IpDao ipDao;

    /**
     * 获取ip进行验证
     *
     * @param canUse
     * @param cnt
     * @return
     */
    public List<Ip> getIp4Check(int canUse, Integer cnt) {
        List<Ip> ips = ipDao.find4Check(canUse);
        Ip.setChecking2All(ips, true);
        ipDao.saveAll(ips);
        Ip.setChecking2All(ips, false);
        return ips;
    }

    public void save(Ip ip) {
        ipDao.save(ip);
    }

    /**
     * 获取最新的经过验证过的ip
     *
     * @param cnt
     * @param protocol
     * @return
     */
    public List<Ip> getLastCanUse(int protocol, Integer cnt) {
        return ipDao.findLastCanUse(protocol);
    }

    public Ip getLastOneCanUse(int protocol, Integer cnt) {
        return ipDao.findLastOneCanUse(protocol);
    }

    public void clearCheckingIp() {
        ipDao.clearCheckingIp();
    }
}
