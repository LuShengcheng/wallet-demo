package cn.lushengcheng.demo.demo.dto.request;

import cn.lushengcheng.demo.demo.enums.TargetType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 支付请求参数
 */
@Data
public class PaymentRequestBody {
    @NotNull(message = "支付订单号不能为空")
    private Long paymentOrderNo;
    @PositiveOrZero(message = "支付金额必须大于0")
    @NotNull(message = "支付金额不能为空")
    @Min(value = 0, message = "支付金额必须大于0")
    private BigDecimal paymentAmount;
    @NotNull(message = "支付账号不能为空")
    private Long paymentAccountId;
    @NotNull(message = "支付类型不能为空")
    private TargetType paymentType;
}
