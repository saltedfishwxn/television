
package com.aaa.util;


/**
 * @Author: 陈建
 * @Date: 2018/12/14 0014 9:34
 * @Version 1.0
 */

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

  /*  public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = MyPageException.class)
    public ModelAndView businessExceptionHandler(HttpServletRequest req, Exception e) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = MyJsonException.class)
    @ResponseBody
    public Map<String, String> jsonExceptionHandler(HttpServletRequest req, Exception e) {

        Map<String, String> re = new HashMap<String, String>();
        re.put("error", "1");
        re.put("msg", e.getMessage());
        return re;
    }*/

}
