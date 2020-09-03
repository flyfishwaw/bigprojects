package com.waw.ipservice;

import com.waw.ipservice.run.IpCheckClient;
import com.waw.publicutils.config.StartCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class IpserviceApplication {

    public static void main(String[] args) {
//        new StartCommand(args);

        SpringApplication.run(IpserviceApplication.class, args);
        new IpCheckClient().run();
    }

}
