package com.panghu.entity;

import com.example.entity.BaseUUIDEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.entity
 * @ClassName: SysRole
 * @Author: wxy
 * @Description: 系统用户角色
 * @Date: 2020/5/5 10:42
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "sys_role")
@EntityListeners(AuditingEntityListener.class)
public class SysRole extends BaseUUIDEntity {

    /**
     * 角色名字
     */
    @Column(name = "name")
    private String name;
    /**
     * 角色代码
     */
    @Column(name = "code")
    private String code;

    @ElementCollection
    @CollectionTable(name="sys_role_permission")
    @MapKeyJoinColumn(name="permission_id")
    private List<SysPermission> permissions;

}
