package com.waw.ipservice.domain;

import com.waw.ipservice.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-waw-ipservice-domain-Ip")
@Data
@Entity
public class Ip implements Serializable {
    /**
     *
     */
    @ApiModelProperty(value = "")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String ip;

    /**
     * 是否正在验证
     */
    @ApiModelProperty(value = "是否正在验证")
    private Boolean checking;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private String addTime = DateUtil.formatDate(new Date());

    /**
     * 最后验证时间
     */
    @ApiModelProperty(value = "最后验证时间")
    private String lastCheckTime;

    /**
     * http|https
     */
    @ApiModelProperty(value = "http|https")
    private Integer protocol;

    /**
     * 来源网站
     */
    @ApiModelProperty(value = "来源网站")
    private String sourceWebsite;

    /**
     * 已经检验了多少次
     */
    @ApiModelProperty(value = "已经检验了多少次")
    private Integer checkTimes = 0;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean canUse = false;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String location;

    /**
     * 匿名程度
     */
    @ApiModelProperty(value = "匿名程度")
    private String anonymous;

    private static final long serialVersionUID = 1L;

    public static void setChecking2All(Iterable<Ip> ips, boolean checking) {
        for (Ip ip : ips) {
            ip.setChecking(checking);
        }
    }
}

