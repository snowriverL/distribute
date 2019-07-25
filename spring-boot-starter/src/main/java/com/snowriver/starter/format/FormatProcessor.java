package com.snowriver.starter.format;

/**
 * 自定义格式化
 */
public interface FormatProcessor {

    /**
     * 定义一个格式化的方法
     * @param obj
     * @param <T>
     * @return
     */
    <T> String format(T obj);

}
