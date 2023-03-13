package cn.lushengcheng.demo.demo.model;

import cn.lushengcheng.demo.demo.enums.TargetType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 充值订单
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_recharge_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RechargeOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 账户id
     **/
    private Long accountId;

    /**
     * 充值金额
     */
    private BigDecimal amount;
    /**
     * 充值渠道
     **/
    private TargetType channel;

    /**
     * 充值状态
     */
    private Boolean status;

}
