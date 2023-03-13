package cn.lushengcheng.demo.demo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 * @since 2023-03-13 13:03:42
 */
@Builder
@Data
public class WithdrawCashResponse {
    private Long id;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 提现方式
     */
    private String withdrawWay;
    /**
     * 提现账户
     */
    private String withdrawAccount;
    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 提现时间
     */
    private Date createTime;

}
