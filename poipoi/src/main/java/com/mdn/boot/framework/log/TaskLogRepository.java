package com.mdn.boot.framework.log;

import com.mdn.boot.business.entity.LogInfoTbl;
import com.mdn.boot.framework.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 日志dao层
 * @author MengDaNai
 * @version v1.0
 * @Date 2019年11月25日
 */
@Repository
public interface TaskLogRepository extends BaseRepository<LogInfoTbl, Long> {

}
