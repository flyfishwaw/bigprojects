package com.waw.ipservice.domain;

import com.waw.ipservice.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-waw-ipservice-domain-UncheckedIp")
@Data
@Component
@Entity(name = "unchecked_ip")
public class UncheckedIp implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String ip;

    /**
     * 上一次验证时间
     */
    @ApiModelProperty(value = "上一次验证时间")
    private String checkTime = DateUtil.formatDate(new Date());
    /**
     * 上一次验证时间
     */
    @ApiModelProperty(value = "添加时间")
    private String addTime = DateUtil.formatDate(new Date());
    /**
     * 验证次数
     */
    @ApiModelProperty(value = "验证次数")
    private Integer checkedTimes = 0;

    /**
     * 来源网站
     */
    @ApiModelProperty(value = "来源网站")
    private String website;

    private static final long serialVersionUID = 1L;

    public UncheckedIp(String ip, String website) {
        this.ip = ip;
        this.website = website;
    }

    public UncheckedIp() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UncheckedIp uncheckedIp = (UncheckedIp) o;
        return ip.equals(uncheckedIp.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}

