package com.waw.ipservice.controller;

import com.waw.ipservice.dao.ForbiddenIpDao;
import com.waw.ipservice.domain.ForbiddenIp;
import com.waw.ipservice.utils.IpUtil;
import com.waw.publicutils.pojo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author flyfish
 * @date 2020-08-25 18:02:33
 * @description
 */
@RestController
public class ForbiddenIpController {
    @Autowired
    private ForbiddenIpDao forbiddenIpDao = null;

    @PostMapping(value = "/forbiddenIp")
    public ResponseData addForbiddenIp(String ip, String website) {
        ResponseData responseData = null;
        try {
            boolean isIp = IpUtil.isIp(ip);
            if (!isIp)
                return new ResponseData(false, 400, "ip格式不正确,应为\"网址:端口号\"");
            ForbiddenIp forbiddenIp = new ForbiddenIp(ip, website);
            forbiddenIpDao.save(forbiddenIp);
            responseData = new ResponseData(true, 200, "插入成功");
        } catch (DataIntegrityViolationException e) {
            responseData = new ResponseData(false, 201, "ip已存在");
        } catch (Exception e) {
            responseData = new ResponseData(false, 400, "插入失败");
        }
        return responseData;
    }
}
