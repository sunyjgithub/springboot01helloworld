package com.atguigu.advice;


import com.atguigu.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * 编写全局异常处理类
 *
 * 如果全部异常处理返回json，
 * 那么可以使用 @RestControllerAdvice 代替 @ControllerAdvice ，这样在方法上就可以不需要添加 @ResponseBody。
 *
 *
 * 如果不需要返回json数据，而要渲染某个页面模板返回给浏览器，那么MyControllerAdvice中可以这么实现
 *
 * ExceptionHandler(value = MyException.class)
 * public ModelAndView myErrorHandler(MyException ex) {
 *     ModelAndView modelAndView = new ModelAndView();
 *     modelAndView.setViewName("error");
 *     modelAndView.addObject("code", ex.getCode());
 *     modelAndView.addObject("msg", ex.getMsg());
 *     return modelAndView;
 * }
 *
 *
 */
@ControllerAdvice
public class MyControllerAdvice {



    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myErrorHandler(MyException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }
}
