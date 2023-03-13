package cn.lushengcheng.demo.demo.controller;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.request.RechargeOrderRequestBody;
import cn.lushengcheng.demo.demo.dto.request.WithdrawCashRequestBody;
import cn.lushengcheng.demo.demo.service.RechargeOrderService;
import cn.lushengcheng.demo.demo.service.WalletLogService;
import cn.lushengcheng.demo.demo.service.WithdrawCashService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/api/wallet")
@Log4j2
public class WalletController {
    private RechargeOrderService rechargeOrderService;
    private WithdrawCashService withdrawCashService;
    private WalletLogService walletLogService;

    @PostMapping("/recharge")
    @ResponseStatus(HttpStatus.CREATED)
    public JsonResult recharge(@Validated
                               @RequestBody RechargeOrderRequestBody rechargeOrderRequestBody) {
        log.info("-------------------调用充值接口------------------");
        rechargeOrderService.recharge(rechargeOrderRequestBody);
        return JsonResult.success("充值成功！");
    }

    @GetMapping("/recharge/{accountId}")
    public JsonResult queryRecharge(
            @PathVariable("accountId") Long accountId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("-------------------查询充值记录------------------");
        return  rechargeOrderService.query(accountId, page, size);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public JsonResult withdraw(@Validated
                               @RequestBody WithdrawCashRequestBody withdrawCashRequestBody) {
        log.info("-------------------调用提现接口------------------");
        withdrawCashService.withdraw(withdrawCashRequestBody);
        return JsonResult.success("提现成功！");
    }

    @GetMapping("/withdraw/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public JsonResult query(@PathVariable("accountId") Long accountId,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("-------------------查询提现记录------------------");
        return withdrawCashService.query(accountId,page, size);
    }

    @GetMapping("/wallet-log/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public JsonResult balance(
            @PathVariable("accountId") Long accountId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        log.info("-------------------查询钱包流水------------------");
        return walletLogService.findAll(accountId,page, size);
    }


}
