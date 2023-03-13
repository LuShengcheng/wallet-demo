package cn.lushengcheng.demo.demo.dto.response;

import cn.lushengcheng.demo.demo.enums.PaymentStatus;
import cn.lushengcheng.demo.demo.enums.TargetType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@Data
@Builder
public class PaymentResponseBody  {

    private Long paymentId;
    /**
     * 支付订单号
     */
    private Long paymentOrderNo;
    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * 支付金额
     */
    private BigDecimal paymentAmount;
    /**
     * 是否有退款
     */
    private Boolean hasRefund;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 支付账号
     */
    private Long paymentAccountId;
    /**
     * 支付类型
     */
    private TargetType paymentType;
    /**
     * 支付状态
     */
    private PaymentStatus paymentStatus;

    /**
     * 备注
     * */
    private String remark;
    /**
     * 下单时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date createTime;

}
