package cn.lushengcheng.demo.demo.controller;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.request.UserRequestBody;
import cn.lushengcheng.demo.demo.model.User;
import cn.lushengcheng.demo.demo.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/wallet/user")
@Log4j2
public class UserController {
    private static final Log LOG = LogFactory.getLog(PaymentController.class);
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JsonResult register(@RequestBody UserRequestBody userRequestBody) {
        log.info("-------------------调用注册接口-----------------------");
        User user = userService.saveOrUpdate(userRequestBody);
        return JsonResult.success("注册成功！", user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object findById(@RequestParam
                           @Validated
                           @NotNull Long userid) {
        log.info("-------------------调用查询接口-----------------------");
        User user = userService.findById(userid);
        return JsonResult.success("查询成功", user);
    }
}
