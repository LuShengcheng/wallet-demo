package cn.lushengcheng.demo.demo.service;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.request.RechargeOrderRequestBody;
import cn.lushengcheng.demo.demo.dto.response.RechargeOrderResponseBody;
import cn.lushengcheng.demo.demo.enums.ActionType;
import cn.lushengcheng.demo.demo.enums.BusinessError;
import cn.lushengcheng.demo.demo.exception.BusinessException;
import cn.lushengcheng.demo.demo.model.Account;
import cn.lushengcheng.demo.demo.model.RechargeOrder;
import cn.lushengcheng.demo.demo.records.WalletLogRecord;
import cn.lushengcheng.demo.demo.repository.RechargeOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Service
@Log4j2
@AllArgsConstructor
public class RechargeOrderService {

    private RechargeOrderRepository rechargeOrderRepository;
    private AccountService accountService;
    private WalletLogService walletLogService;

    public void recharge(RechargeOrderRequestBody rechargeOrderRequestBody) {

        if (rechargeOrderRepository.existsByOrderId(rechargeOrderRequestBody.getOrderId())) {
            throw new BusinessException(BusinessError.ORDER_IS_EXIST);
        }

        Account account = accountService.findAccountById(rechargeOrderRequestBody.getAccountId());

        RechargeOrder rechargeOrder = RechargeOrder.builder()
                .orderId(rechargeOrderRequestBody.getOrderId())
                .accountId(rechargeOrderRequestBody.getAccountId())
                .channel(rechargeOrderRequestBody.getChannel())
                .amount(rechargeOrderRequestBody.getAmount())
                .status(Boolean.TRUE).build();
        WalletLogRecord walletLogRecord = new WalletLogRecord(
                rechargeOrderRequestBody.getAccountId(),
                UUID.randomUUID().toString().replace("-", ""),
                ActionType.RECHARGE,
                rechargeOrderRequestBody.getChannel(),
                rechargeOrderRequestBody.getAmount()
        );

        walletLogService.save(walletLogRecord);
        rechargeOrderRepository.save(rechargeOrder);

        account.setBalanceFee(account.getBalanceFee().add(rechargeOrderRequestBody.getAmount()));
        accountService.save(account);
    }

    public JsonResult query(Long accountId, Integer page, Integer size) {

        accountService.findAccountById(accountId);

        log.info("query recharge order page{},size{}", page, size);
        List<RechargeOrderResponseBody> rechargeOrderResponseBodies = rechargeOrderRepository
                .findAllByAccountId(accountId, PageRequest.of(page - 1, size))
                .stream()
                .map(this::mapToRechargeOrderResponseBody).toList();

        return JsonResult.success("查询成功！", rechargeOrderResponseBodies, rechargeOrderResponseBodies.size());

    }

    private RechargeOrderResponseBody mapToRechargeOrderResponseBody(RechargeOrder rechargeOrder) {
        return RechargeOrderResponseBody.builder()
                .id(rechargeOrder.getId())
                .orderId(rechargeOrder.getOrderId())
                .amount(rechargeOrder.getAmount())
                .channel(rechargeOrder.getChannel().getValue())
                .status(rechargeOrder.getStatus())
                .createTime(rechargeOrder.getCreateTime())
                .build();
    }
}
