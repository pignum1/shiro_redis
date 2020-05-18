package com.panghu.dao;

import com.example.dao.BaseRepository;
import com.panghu.entity.SysPermission;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.dao
 * @ClassName: SysPermissionDao
 * @Author: wxy
 * @Description: 权限dao
 * @Date: 2020/5/5 14:07
 * @Version: 1.0
 */
@Repository
public interface SysPermissionDao extends BaseRepository<SysPermission, String> {
}
