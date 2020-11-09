package com.mdn.boot.framework.util.functionalinterface;

/**
 * 自定义的Function抛出Exception异常
 * @ClassName: FunctionCustomize.java
 * @author MengDaNai
 * @version 1.0
 * @Date 2019年1月29日 下午6:04:34
 */
@FunctionalInterface
public interface FunctionCustomize<T, R> {
	
	R apply(T t) throws Exception;
}
