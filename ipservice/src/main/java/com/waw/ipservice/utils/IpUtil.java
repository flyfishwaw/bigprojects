package com.waw.ipservice.utils;

import com.waw.ipservice.config.IpCheckConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author flyfish
 * @date 2020-08-21 11:31:10
 * @description
 */
public class IpUtil {
    //    @Autowired
    private static IpCheckConfig ipCheckConfig = SpringContextUtil.getBean(IpCheckConfig.class);

    public static boolean isJustIp(String ip) {
        String[] datas = ip.split("\\.");//.是有意义的,所以要转义
        if (datas.length == 4 && datas[0] != "0" && datas[3] != "0") {
            for (String s : datas) {
                try {
                    int i = Integer.parseInt(s);
                    if (i > 255) return false;
                } catch (Exception e) {
                    return false;
                }

            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean isIp(String ipAndPort) {
        String[] datas = ipAndPort.split(":");
        if (datas.length == 2) {
            return isJustIp(datas[0]) && isPort(datas[1]);
        }
        return false;
    }

    public static boolean isPort(String port) {
        try {
            int i = Integer.parseInt(port);
            if (i < 1 || i > 65535) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断ip是否可用
     *
     * @param ip
     * @param protocol
     * @return
     */
    public static boolean checkCanUse(String ip, String protocol) {
        if (protocol != "http" && protocol != "https") return false;
        String[] ipAndPort = ip.split(":");
        try {
            OkHttpClient client = new OkHttpClient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (protocol.equalsIgnoreCase("http")) {
                builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1]))));
            } else if (protocol.equalsIgnoreCase("https")) {
                builder.proxy(new Proxy(Proxy.Type.valueOf("https"), new InetSocketAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1]))));
            }
            Request request = new Request.Builder().url(ipCheckConfig.getUrl()).build();
            Response response = client.newCall(request).execute();
            response.close();
            if (response.isSuccessful())
                return true;
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    public static int getIpProtocol(String ip) {
        boolean httpCheckResult = IpUtil.checkCanUse(ip, "http");
        boolean httpsCHeckResult = IpUtil.checkCanUse(ip, "https");
        int checkResult = -1;
        if (httpCheckResult && !httpsCHeckResult) {
            checkResult = 0;
        } else if (httpCheckResult && httpsCHeckResult) {
            checkResult = 2;
        } else if (!httpCheckResult && httpsCHeckResult) {
            checkResult = 1;
        }
        return checkResult;
    }
}
