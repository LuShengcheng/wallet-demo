package cn.lushengcheng.demo.demo.records;

import cn.lushengcheng.demo.demo.enums.ActionType;
import cn.lushengcheng.demo.demo.enums.TargetType;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public record WalletLogRecord(Long accountId,
                              String serialNumber,
                              ActionType actionType,
                              TargetType targetType,
                              BigDecimal amount) {
}
