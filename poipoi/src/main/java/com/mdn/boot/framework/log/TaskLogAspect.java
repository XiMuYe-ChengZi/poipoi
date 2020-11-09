package com.mdn.boot.framework.log;

import com.alibaba.fastjson.JSON;
import com.mdn.boot.business.entity.UserInfoTbl;
import com.mdn.boot.framework.config.MdnConfig;
import com.mdn.boot.framework.exception.BaseException;
import com.mdn.boot.framework.util.ThreadParameterUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.domain.Page;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Aspect
@Configuration
public class TaskLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(TaskLogAspect.class);

	private TaskLogDao taskLogDao;
	private MdnConfig mdnConfig;

	@Autowired
	public TaskLogAspect(TaskLogDao taskLogDao, MdnConfig mdnConfig) {
		super();
		this.taskLogDao = taskLogDao;
		this.mdnConfig = mdnConfig;
	}

	// 设置切点表达式
	@Pointcut("(execution(* com.mdn.boot.business..*(..)) && @annotation(com.mdn.boot.framework.log.TaskLog))")
	private void pointcut() {

	}

	/**
	 * 环绕通知
	 * @param proceedingJoinPoint
	 */
	@Around(value = "pointcut()")
	private Object process(ProceedingJoinPoint proceedingJoinPoint){
		Object result = null;
		if (!mdnConfig.getLog()) {
			try {
				result = proceedingJoinPoint.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return result;
		}
		logger.info("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
		String userId = "null";
		if (StringUtils.hasText(WebUtils.getSessionId(ThreadParameterUtil.getRequest()))){
			userId = JSON.parseObject(WebUtils.getSessionAttribute(ThreadParameterUtil.getRequest(), "userInfo").toString(), UserInfoTbl.class).getUserId().toString();
		}
		final String setUserId = userId;
		//获取被代理的方法
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		LocalDateTime startTime = LocalDateTime.now();
		//获得被代理方法的参数
		logger.info("用户:[{}] 在 [{}] 调用类:[{}] 方法:[{}] 开始", userId, startTime, method.getDeclaringClass().getName(), method.getName());
		Object[] params = proceedingJoinPoint.getArgs();
		Stream.iterate(1, i -> i + 1).limit(params.length).forEach(i -> {
			Object param = params[i-1];
			logger.info("参数{} 类型: [{}] 参数值: [{}]", i, param.getClass().getName(), JSON.toJSONString(param));
		});
		//首先展示自定义的日志逻辑
		TaskLog taskLog = method.getAnnotation(TaskLog.class);
		SingleLog[] logs = taskLog.singleLog();
		//taskLogDao.saveLog("用户:["+setUserId+"] 在 ["+startTime+"] 调用类:["+method.getDeclaringClass().getName()+"] 方法:["+method.getName()+"] 开始", params, method.getName(), LevelEnum.INFO);
		//遍历所有自定义的日志记录
		Arrays.asList(logs).stream().forEach(log -> {
			String logStr = log.value();
			LevelEnum level = log.levelEnum();
			logStr = parseKey(logStr, method, params);
			taskLogDao.saveLog(logStr, "用户:["+setUserId+"] 在 ["+startTime+"] 调用类:["+method.getDeclaringClass().getName()+"] 方法:["+method.getName()+"] 开始", params, method.getName(), level);
			switch (level) {
			case INFO:
				logger.info(logStr);
				break;
			case DEBUG:
				logger.debug(logStr);
				break;
			case WARN:
				logger.warn(logStr);
				break;
			case ERROR:
				logger.error(logStr);
				break;
			case TRACE:
				logger.trace(logStr);
				break;
			default:
				break;
			}
		});
		//处理环绕通知之前的逻辑
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// e.printStackTrace();
			if (e instanceof BaseException){
				BaseException baseException = (BaseException) e;
				logger.error("在执行代理方法时出现错误，异常信息为[{}]", baseException.getErrorMessage());
				taskLogDao.saveLog("[[异常]]","在执行代理方法时出现错误，异常信息为["+baseException.getErrorMessage()+"],具体信息是["+JSON.toJSONString(Arrays.asList(e.getStackTrace()).stream().filter(f -> f.getClassName().indexOf("danfan")>-1).collect(Collectors.toList()))+"]", params, method.getName(), LevelEnum.ERROR);
				logger.info("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
				throw (BaseException) e;
			} else {
				logger.error("在执行代理方法时出现错误，异常信息为[{}]", e.getMessage());
				taskLogDao.saveLog("[[异常]]","在执行代理方法时出现错误，异常信息为["+e.getMessage()+"],具体信息是["+JSON.toJSONString(Arrays.asList(e.getStackTrace()).stream().filter(f -> f.getClassName().indexOf("danfan")>-1).collect(Collectors.toList()))+"]", params, method.getName(), LevelEnum.ERROR);
				logger.info("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
				throw (RuntimeException) e;
			}
		}
		LocalDateTime endTime = LocalDateTime.now();
		Duration duration = Duration.between(startTime, endTime);
		//处理环绕通知之后的逻辑
		logger.info("用户:[{}] 在 [{}] 调用类:[{}] 方法:[{}] 结束, 共耗时:[{}]ms", userId,
				endTime, method.getDeclaringClass().getName(), method.getName(), duration.toMillis());
		taskLogDao.saveAfterLog("[[后置切面]]","用户:["+setUserId+"] 在 ["+startTime+"] 调用类:["+method.getDeclaringClass().getName()+"] 方法:["+method.getName()+"] 结束, 共耗时:["+duration.toMillis()+"]ms", params, method.getName(), LevelEnum.INFO, duration.toMillis());
		String resultStr;
		if(result instanceof Page<?>) {
			Page<?> pageResult = (Page<?>) result;
			resultStr = JSON.toJSONString(pageResult.getContent());
			logger.info("分页信息为:每页[{}]条  当前第[{}]页  共[{}]页", pageResult.getSize(), pageResult.getNumber() + 1, pageResult.getTotalPages());
			logger.info("方法调用返回值是:[{}]", resultStr);
		}else {
			resultStr = JSON.toJSONString(result);
			logger.info("方法调用返回值是:[{}]", resultStr);
		}
		logger.info("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
		return result;
	}

	/**
	 * 转换key
	 * @param key
	 * @param method
	 * @param args
	 * @return
	 */
    private String parseKey(String key, Method method, Object [] args){
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
            new LocalVariableTableParameterNameDiscoverer();
        String [] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        if(! Objects.isNull(paraNameArr)) {
        	for(int i = 0;i < paraNameArr.length;i++){
                context.setVariable(paraNameArr[i], args[i]);
            }
        }
        List<String> pList = descFormat(key);//获取#p0这样的表达式
        //将p0作为参数放入SPEL上下文中
        for(String p:pList) {
        	context.setVariable(p.substring(1), args[Integer.valueOf(p.substring(2))]);
        }
        return parser.parseExpression(key).getValue(context,String.class);
    }

    /**
     * 提取出#p[数字]这样的表达式
     * @param desc
     * @return
     */
    private static List<String> descFormat(String desc){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("#p[0-9]+");
        Matcher matcher = pattern.matcher(desc);
        while(matcher.find()){
            String t = matcher.group(0);
            list.add(t);
        }
        return list;
    }

}
