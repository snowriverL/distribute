package com.snowriver.starter.format;

import com.alibaba.fastjson.JSON;

/**
 *
 */
public class JsonFormatProcessor implements FormatProcessor{

    public <T> String format(T obj) {
        return "" + JSON.toJSONString(obj);
    }
}
