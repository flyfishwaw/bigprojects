package com.waw.ipservice.domain;

import com.waw.ipservice.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com-waw-ipservice-domain-CheckedIp")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Component
@Entity(name = "checked_ip")
public class CheckedIp implements Serializable {
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
     * 验证时间
     */
    @ApiModelProperty(value = "验证时间")
    private Date checkTime = new Date();

    /**
     * 协议
     */
    @ApiModelProperty(value = "协议")
    private Integer protocol;

    /**
     * 来源网站
     */
    @ApiModelProperty(value = "来源网站")
    private String website;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private String addTime = DateUtil.formatDate(new Date());

    private static final long serialVersionUID = 1L;

    public CheckedIp() {
    }

    public CheckedIp(String ip, Integer protocol, String website) {
        this.ip = ip;
        this.protocol = protocol;
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedIp checkedIp = (CheckedIp) o;
        return ip.equals(checkedIp.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}

