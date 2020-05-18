package com.panghu.entity;

import com.example.entity.BaseUUIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.entity
 * @ClassName: SysSession
 * @Author: wxy
 * @Description: 系统用户session
 * @Date: 2020/5/5 10:14
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "sys_session")
@EntityListeners(AuditingEntityListener.class)
public class SysSession extends BaseUUIDEntity {
    private String sessionId;

    @Column(name = "account")
    private String account;

    @Column(name = "ip")
    private String ip;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone ="GMT+8", pattern ="yyyy-MM-dd hh:mm:ss")
    private Date startTime;
}
