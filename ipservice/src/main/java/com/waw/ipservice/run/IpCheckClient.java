package com.waw.ipservice.run;

import com.waw.ipservice.task.CheckedIpCheckTask;
import com.waw.ipservice.task.NotUseIpCheckTask;
import com.waw.ipservice.task.UncheckedIpCheckTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author flyfish
 * @date 2020-08-25 14:52:33
 * @description
 */
@Component
public class IpCheckClient {
    @Autowired
    UncheckedIpCheckTask uncheckedIpCheckTask;
    @Autowired
    NotUseIpCheckTask notUseIpCheckTask;
    @Autowired
    CheckedIpCheckTask checkedIpCheckTask;
    @Async("ipCheckPool")
    public void run() {
        while (true) {
            uncheckedIpCheckTask.check();
            notUseIpCheckTask.check();
            checkedIpCheckTask.check();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {

            }

        }
    }
}
