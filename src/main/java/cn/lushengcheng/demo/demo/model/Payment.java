package cn.lushengcheng.demo.demo.model;

import cn.lushengcheng.demo.demo.enums.PaymentStatus;
import cn.lushengcheng.demo.demo.enums.TargetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_payment")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    /**
     * 支付订单号
     */
    @Column(unique = true)
    private Long paymentOrderNo;
    /**
     * 流水号
     */
    @Column(unique = true)
    private String serialNumber;

    /**
     * 支付金额
     */
    @PositiveOrZero
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
     * 支付类型 0: 支付宝 1: 微信 2：银行 3：钱包
     */
    @Enumerated(EnumType.ORDINAL)
    private TargetType paymentType;
    /**
     * 支付状态 0：已支付 1：未支付 2：支付失败 3：退款
     */
    private PaymentStatus paymentStatus;

}
