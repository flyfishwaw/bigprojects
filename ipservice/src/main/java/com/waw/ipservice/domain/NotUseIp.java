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

@ApiModel(value = "com-waw-ipservice-domain-NotUseIp")
@Data
@Component
@Entity(name = "not_use_ip")
public class NotUseIp implements Serializable {
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
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private String addTime = DateUtil.formatDate(new Date());
    /**
     * 上一次验证时间
     */
    @ApiModelProperty(value = "上一次验证时间")
    private String checkTime = DateUtil.formatDate(new Date());
    /**
     * 来源网站
     */
    @ApiModelProperty(value = "来源网站")
    private String website;
    private static final long serialVersionUID = 1L;

    public NotUseIp() {
    }

    public NotUseIp(String ip, String website) {
        this.ip = ip;
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotUseIp notUseIp = (NotUseIp) o;
        return ip.equals(notUseIp.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}

