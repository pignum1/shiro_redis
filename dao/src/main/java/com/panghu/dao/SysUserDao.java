package com.panghu.dao;

import com.example.dao.BaseRepository;
import com.panghu.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.dao
 * @ClassName: SysUserDao
 * @Author: wxy
 * @Description: 系统用户dao
 * @Date: 2020/5/3 16:40
 * @Version: 1.0
 */
@Repository
public interface SysUserDao extends BaseRepository<SysUser,String> {
    SysUser findByAccount(String account);
}
