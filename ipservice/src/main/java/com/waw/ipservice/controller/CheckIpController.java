package com.waw.ipservice.controller;

import com.waw.ipservice.domain.CheckedIp;
import com.waw.ipservice.run.IpCheckClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author flyfish
 * @date 2020-08-25 16:15:39
 * @description
 */
@RestController
public class CheckIpController {
    @Autowired
    IpCheckClient ipCheckClient;

    @GetMapping(value = "/checkIp")
    public void CheckedIp() {
        ipCheckClient.run();
    }
}
