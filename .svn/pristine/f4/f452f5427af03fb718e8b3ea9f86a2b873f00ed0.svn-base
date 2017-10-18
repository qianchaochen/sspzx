package com.gionee.ssp.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author dingyw
 * 如果正常返回广告，并且请求参数带了callback=XXX，则返回jsonp格式的字符串
 *
 * 2017年9月6日
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{
    public JsonpAdvice() {
        super("callback"); //请求中的参数带的callback=XXX
    }

}
