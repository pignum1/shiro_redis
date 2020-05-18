package com.panghu.dao;

import com.example.dao.BaseRepository;
import com.panghu.entity.SysRole;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.dao
 * @ClassName: SysRoleDao
 * @Author: wxy
 * @Description: 角色Dao
 * @Date: 2020/5/5 14:07
 * @Version: 1.0
 */
@Repository
public interface SysRoleDao extends BaseRepository<SysRole,String> {
}
