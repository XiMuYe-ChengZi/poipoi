package com.mdn.boot.framework.log;

import com.alibaba.fastjson.JSON;
import com.mdn.boot.business.entity.LogInfoTbl;
import com.mdn.boot.business.entity.UserInfoTbl;
import com.mdn.boot.business.util.date.DateUtil;
import com.mdn.boot.framework.constant.SystemConstant;
import com.mdn.boot.framework.util.ThreadParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Repository
public class TaskLogDao {

	private TaskLogRepository taskLogRepository;

	@Autowired
	public TaskLogDao(TaskLogRepository taskLogRepository) {
		this.taskLogRepository = taskLogRepository;
	}

	private final Supplier<LogInfoTbl> SUPPLIER = () -> {
		LogInfoTbl logInfoTbl = new LogInfoTbl();
		if (StringUtils.hasText(WebUtils.getSessionId(ThreadParameterUtil.getRequest()))){
			UserInfoTbl user = JSON.parseObject(WebUtils.getSessionAttribute(ThreadParameterUtil.getRequest(), "userInfo").toString(), UserInfoTbl.class);
			String setUserId = user.getUserId()+"H"+ DateUtil.localDateTimeToDate(user.getRecentTime()).getTime();
			logInfoTbl.setUserId(setUserId);
		}
		return logInfoTbl;
	};

	public void saveAfterLog(String log, String methodTrack, Object[] params, String methodName, LevelEnum levelEnum, long millis) {
		UnaryOperator<LogInfoTbl> deal = l -> {
			l.setTimeConsuming(Integer.parseInt(Long.toString(millis)));
			return l;
		};
		adsf(log, methodTrack, params, methodName, levelEnum, deal);
	}

	public void saveLog(String log, String methodTrack, Object[] params, String methodName, LevelEnum levelEnum) {
		adsf(log, methodTrack, params, methodName, levelEnum, logInfoTbl -> logInfoTbl);
	}


	private void adsf(String log, String methodTrack, Object[] params, String methodName, LevelEnum levelEnum, UnaryOperator<LogInfoTbl> deal) {
		LogInfoTbl logInfoTbl = SUPPLIER.get();
		logInfoTbl.setUrl(ThreadParameterUtil.getRequest().getServletPath());
		logInfoTbl.setParameter(JSON.toJSONString(params));
		logInfoTbl.setMethodName(methodName);
		logInfoTbl.setLogInformation(log);
		logInfoTbl.setMethodTrack(methodTrack);
		logInfoTbl.setLevel(levelEnum.toString());
		logInfoTbl.setUuid(ThreadParameterUtil.getUUID());
		logInfoTbl.setAddTime(LocalDateTime.now());
		logInfoTbl.setDeleteFlag(SystemConstant.DELETED_TRUE);
		LogInfoTbl apply = deal.apply(logInfoTbl);
		taskLogRepository.save(apply);
	}
}
