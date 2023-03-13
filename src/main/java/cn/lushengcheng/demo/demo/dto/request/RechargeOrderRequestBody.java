package cn.lushengcheng.demo.demo.dto.request;

import cn.lushengcheng.demo.demo.enums.TargetType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Data
public class RechargeOrderRequestBody {

    /**
     * 订单id
     **/
    @NotNull(message = "订单id不能为空")
    private Long orderId;
    /**
     * 账户id
     **/
    @NotNull(message = "账户id不能为空")
    private Long accountId;
    /**
     * 充值金额
     */
    @NotNull(message = "充值金额不能为空")
    @Min(value = 0, message = "支付金额必须大于0")
    private BigDecimal amount;
    /**
     * 充值渠道
     */
    @NotNull(message = "充值渠道道不能为空")
    private TargetType channel;
}
