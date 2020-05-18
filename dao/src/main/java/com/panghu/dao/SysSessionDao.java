package com.panghu.dao;

import com.example.dao.BaseRepository;
import com.panghu.entity.SysSession;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.dao
 * @ClassName: SysSessionDao
 * @Author: wxy
 * @Description: session
 * @Date: 2020/5/5 14:08
 * @Version: 1.0
 */
@Repository
public interface SysSessionDao extends BaseRepository<SysSession, String> {
}
