package cn.lushengcheng.demo.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 退款请求参数
 */
@Data
public class RefundRequestBody {
    @NotNull(message = "订单号不能为空")
    private Long paymentOrderNo;
    @NotNull(message = "退款金额不能为空")
    @Min(value = 0, message = "退款金额不能是负数")
    private BigDecimal refundAmount;
    @NotNull(message = "退款原因不能为空")
    private String refundReason;
    @NotNull(message = "退款备注不能为空")
    private String refundRemark;

}
