package cn.lushengcheng.demo.demo.service;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.request.WithdrawCashRequestBody;
import cn.lushengcheng.demo.demo.dto.response.WithdrawCashResponse;
import cn.lushengcheng.demo.demo.enums.ActionType;
import cn.lushengcheng.demo.demo.enums.BusinessError;
import cn.lushengcheng.demo.demo.enums.TargetType;
import cn.lushengcheng.demo.demo.enums.WithDrawType;
import cn.lushengcheng.demo.demo.exception.BusinessException;
import cn.lushengcheng.demo.demo.model.Account;
import cn.lushengcheng.demo.demo.model.WithdrawCash;
import cn.lushengcheng.demo.demo.records.WalletLogRecord;
import cn.lushengcheng.demo.demo.repository.WithdrawCashRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Service
@AllArgsConstructor
@Log4j2
public class WithdrawCashService {
    private WithdrawCashRepository withdrawCashRepository;
    private AccountService accountService;
    private WalletLogService walletLogService;

    private void save(WithdrawCash withdrawCash) {
        withdrawCashRepository.save(withdrawCash);
    }

    public void withdraw(WithdrawCashRequestBody withdrawCashRequestBody) {

        Account account = accountService.findAccountById(withdrawCashRequestBody.getAccountId());
        BigDecimal balanceFee = account.getBalanceFee();
        BigDecimal withdrawAmount = withdrawCashRequestBody.getAmount();

        log.debug("扣减账户余额：{}", withdrawAmount);
        if (balanceFee.compareTo(new BigDecimal(0)) == 0 || balanceFee.compareTo(withdrawAmount) < 0) {
            throw new BusinessException(BusinessError.BALANCE_FEE_IS_NOT_ENOUGH);
        } else {
            log.debug("更新账户信息：{}", account.getAccountId());
            account.setBalanceFee(account.getBalanceFee().subtract(withdrawAmount));
            accountService.save(account);
            log.debug("保存记录：{}", account.getBalanceFee());
            String serialNumber = UUID.randomUUID().toString().replace("-", "");
            WithdrawCash withdrawCash = WithdrawCash.builder()
                    .serialNumber(serialNumber)
                    .withdrawWay(withdrawCashRequestBody.getWithdrawWay())
                    .withdrawAccount(withdrawCashRequestBody.getWithdrawAccount())
                    .accountId(withdrawCashRequestBody.getAccountId())
                    .amount(withdrawAmount).build();
            save(withdrawCash);
            log.debug("保存流水：{}", withdrawCash.getAmount());
            WalletLogRecord walletLogRecord = new WalletLogRecord(
                    withdrawCash.getAccountId(),
                    serialNumber,
                    ActionType.WITHDRAW,
                    convertToTarget(withdrawCash.getWithdrawWay()),
                    withdrawCash.getAmount()
            );
            walletLogService.save(walletLogRecord);
        }
    }

    private TargetType convertToTarget(WithDrawType withdrawWay) {
        return TargetType.valueOf(withdrawWay.name());
    }

    public JsonResult query(Long accountId, Integer page, Integer size) {
        accountService.findAccountById(accountId);
        List<WithdrawCashResponse> withdrawCashResponses = withdrawCashRepository
                .findAllByAccountId(accountId, PageRequest.of(page - 1, size))
                .orElseThrow(() -> new BusinessException(BusinessError.DATA_IS_EMPTY))
                .stream()
                .map(this::mapToWithdrawCashResponse).toList();
        return JsonResult.success("查询成功", withdrawCashResponses, withdrawCashResponses.size());
    }

    private WithdrawCashResponse mapToWithdrawCashResponse(WithdrawCash withdrawCash) {
        return WithdrawCashResponse.builder()
                .id(withdrawCash.getId())
                .serialNumber(withdrawCash.getSerialNumber())
                .withdrawAccount(withdrawCash.getWithdrawAccount())
                .amount(withdrawCash.getAmount())
                .withdrawWay(withdrawCash.getWithdrawWay().getValue())
                .createTime(withdrawCash.getCreateTime()).build();

    }
}
