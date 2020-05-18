package com.panghu.entity;

import com.example.entity.BaseUUIDEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.entity
 * @ClassName: SysPermission
 * @Author: wxy
 * @Description: 权限表
 * @Date: 2020/5/5 10:53
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "sys_permission")
@EntityListeners(AuditingEntityListener.class)
public class SysPermission extends BaseUUIDEntity {
    /**
     * 名字
     */
    @Column(name = "name")
    private String name;
    /**
     * 路径
     */
    @Column(name = "url")
    private String url;

}
