package cn.lushengcheng.demo.demo.dto.request;

import cn.lushengcheng.demo.demo.enums.WithDrawType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Data
public class WithdrawCashRequestBody {
    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 提现方式 0：支付宝 1：微信 2：银行
     */
    @NotNull(message = "提现方式不能为空")
    private WithDrawType withdrawWay;
    /**
     * 提现账户
     */
    private String withdrawAccount;
    /**
     * 提现金额
     */
    private BigDecimal amount;
}
