package com.waw.ipservice.run;

import com.waw.ipservice.task.CheckedIpCheckTask;
import com.waw.ipservice.task.NotUseIpCheckTask;
import com.waw.ipservice.task.UncheckedIpCheckTask;
import com.waw.ipservice.utils.SpringContextUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author flyfish
 * @date 2020-08-25 14:52:33
 * @description
 */
//@Component
public class IpCheckClient {
    //    @Autowired
    UncheckedIpCheckTask uncheckedIpCheckTask;
    //    @Autowired
    NotUseIpCheckTask notUseIpCheckTask;
    //    @Autowired
    CheckedIpCheckTask checkedIpCheckTask;

    //    @Async("ipCheckPool")
    public void run() {
        uncheckedIpCheckTask = SpringContextUtil.getBean("uncheckedIpCheckTask", UncheckedIpCheckTask.class);
        notUseIpCheckTask = SpringContextUtil.getBean("notUseIpCheckTask", NotUseIpCheckTask.class);
        checkedIpCheckTask = SpringContextUtil.getBean("checkedIpCheckTask", CheckedIpCheckTask.class);
        while (true) {
            uncheckedIpCheckTask.check();
            notUseIpCheckTask.check();
            checkedIpCheckTask.check();
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {

            }

        }
    }
}
