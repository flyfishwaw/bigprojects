package com.waw.eurekaservermaster.lister;

import com.netflix.appinfo.InstanceInfo;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author flyfish
 * @date 2020-07-30 11:25:29
 * @description 服务上下线监控
 */
@Component
public class EurekaStateChangeLister {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        //如果服务下线发送邮件通知管理员
        System.err.println(event.getServerId() + "\t" + event.getAppName() + "服务下线");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.err.println(instanceInfo.getAppName() + "进行注册");
    }

//    @EventListener
//    public void listen(EurekaInstanceRenewedEvent event) {
//        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 进行续约");
//    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.err.println("注册中心启动");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.err.println("Eureka Server启动");
    }
}
