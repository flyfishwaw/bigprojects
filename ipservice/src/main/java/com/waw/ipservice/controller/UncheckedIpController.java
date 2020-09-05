//package com.waw.ipservice.controller;
//
//import com.waw.ipservice.utils.IpUtil;
//import com.waw.publicutils.pojo.ResponseData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author flyfish
// * @date 2020-08-20 16:21:58
// * @description
// */
//@RestController
//public class UncheckedIpController {
//    @Autowired
//    UncheckedIpDao uncheckedIpDao = null;
//
//    /**
//     * 把传过来的ip插入到unchecked_ip表
//     *
//     * @param ip
//     * @param website
//     * @return
//     */
//    @PostMapping(value = "/ip")
//    public ResponseData addIp(String ip, String website) {
//        UncheckedIp uncheckedIp = new UncheckedIp(ip, website);
//
//        ResponseData responseData = null;
//        try {
//            boolean isIp = IpUtil.isIp(ip);
//            if (!isIp)
//                return new ResponseData(false, 400, "ip格式不正确,应为\"网址:端口号\"");
//            uncheckedIpDao.save(uncheckedIp);
//            responseData = new ResponseData(true, 200, "插入成功");
//        } catch (DataIntegrityViolationException e) {
//            responseData = new ResponseData(false, 201, "ip已存在");
//        } catch (Exception e) {
//            responseData = new ResponseData(false, 400, "插入失败");
//        }
//        return responseData;
//    }
//}
