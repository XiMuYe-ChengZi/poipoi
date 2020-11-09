package com.mdn.boot.framework.util.functionalinterface;

/**
 * 自定义的Predicate抛出Exception异常
 * @ClassName: PredicateCustomize.java
 * @author MengDaNai
 * @version 1.0
 * @Date 2019年1月29日 下午6:04:46
 */
@FunctionalInterface
public interface PredicateCustomize<T> {

	boolean test(T t) throws Exception;
}
