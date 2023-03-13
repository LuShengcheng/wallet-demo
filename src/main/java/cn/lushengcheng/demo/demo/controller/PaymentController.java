package cn.lushengcheng.demo.demo.controller;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.request.PaymentRequestBody;
import cn.lushengcheng.demo.demo.dto.request.RefundRequestBody;
import cn.lushengcheng.demo.demo.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@AllArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/wallet/payment/pay")
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping
    public JsonResult pay(@RequestBody
                          @Validated PaymentRequestBody paymentRequestBody) {
        log.info("-------------------调用支付接口-------------------");
        paymentService.pay(paymentRequestBody);
        return JsonResult.success("支付成功");
    }

    @GetMapping("/{accountId}")
    public JsonResult query(@PathVariable("accountId") Long accountId,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer size){
        log.info("-------------------调用查询接口-------------------");
        return paymentService.query(accountId,page,size);
    }

    /**
     * 退款接口
     */
    @PostMapping("/refund")
    public JsonResult refund(@RequestBody @Validated RefundRequestBody refundRequestBody) {
        log.info("-------------------调用退款接口-------------------");
        paymentService.refund(refundRequestBody);
        return JsonResult.success();
    }

}
