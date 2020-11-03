package com.test.servicebase.exceptionhandler;

import com.test.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理-------");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了Airthme123ticException异常处理--------");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        //e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
