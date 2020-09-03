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

@ApiModel(value = "com-waw-ipservice-domain-ForbiddenIp")
@Data
@Component
@Entity(name = "forbidden_ip")
public class ForbiddenIp implements Serializable {
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
     * 被禁时间
     */
    @ApiModelProperty(value = "被禁时间")
    private String forbiddenTime = DateUtil.formatDate(new Date());

    /**
     * 被禁网站
     */
    @ApiModelProperty(value = "被禁网站")
    private String website;

    private static final long serialVersionUID = 1L;

    public ForbiddenIp() {
    }

    public ForbiddenIp(String ip, String website) {
        this.ip = ip;
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForbiddenIp forbiddenIp = (ForbiddenIp) o;
        return ip.equals(forbiddenIp.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}

