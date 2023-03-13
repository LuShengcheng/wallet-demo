package cn.lushengcheng.demo.demo.service;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.response.WalletLogResponseBody;
import cn.lushengcheng.demo.demo.model.WalletLog;
import cn.lushengcheng.demo.demo.records.WalletLogRecord;
import cn.lushengcheng.demo.demo.repository.WalletLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Service
@AllArgsConstructor
public class WalletLogService {

    private WalletLogRepository walletLogRepository;
    private AccountService accountService;

    public void save(WalletLogRecord walletLogRecord) {
        WalletLog walletLog = WalletLog.builder()
                .accountId(walletLogRecord.accountId())
                .serialNumber(walletLogRecord.serialNumber())
                .actionType(walletLogRecord.actionType())
                .targetType(walletLogRecord.targetType())
                .amount(walletLogRecord.amount()).build();
        walletLogRepository.save(walletLog);
    }

    public JsonResult findAll(Long accountId, int page, int size) {
        accountService.findAccountById(accountId);

        List<WalletLogResponseBody> walletLogResponseBodies = walletLogRepository
                .findAllByAccountId(accountId, PageRequest.of(page - 1, size))
                .stream()
                .map(this::mapToWalletLogResponse).toList();
        return JsonResult.success("查询成功！", walletLogResponseBodies, walletLogResponseBodies.size());
    }

    private WalletLogResponseBody mapToWalletLogResponse(WalletLog walletLog) {
        return WalletLogResponseBody.builder()
                .id(walletLog.getId())
                .serialNumber(walletLog.getSerialNumber())
                .actionType(walletLog.getActionType().getValue())
                .targetType(walletLog.getTargetType().getValue())
                .amount(walletLog.getAmount())
                .createTime(walletLog.getCreateTime()).build();
    }

}
