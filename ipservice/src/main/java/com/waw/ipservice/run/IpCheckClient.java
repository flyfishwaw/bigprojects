package com.waw.ipservice.run;

import com.waw.ipservice.service.IpServie;
import com.waw.ipservice.task.CanUseIpCheckTask;
import com.waw.ipservice.task.NotCanUseIpCheckTask;
import com.waw.ipservice.utils.SpringContextUtil;

/**
 * @author flyfish
 * @date 2020-08-25 14:52:33
 * @description
 */
//@Component
public class IpCheckClient {

    //    @Async("ipCheckPool")
    public void run() {
        NotCanUseIpCheckTask uncheckedIpCheckTask = SpringContextUtil.getBean("notCanUseIpCheckTask", NotCanUseIpCheckTask.class);
        CanUseIpCheckTask checkedIpCheckTask = SpringContextUtil.getBean("canUseIpCheckTask", CanUseIpCheckTask.class);
        IpServie ipServie = SpringContextUtil.getBean("ipServie", IpServie.class);

        ipServie.clearCheckingIp();
        while (true) {
            uncheckedIpCheckTask.check();
            checkedIpCheckTask.check();
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {

            }

        }
    }
}
