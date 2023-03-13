package cn.lushengcheng.demo.demo.model;

import cn.lushengcheng.demo.demo.enums.WithDrawType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_withdraw_cash")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawCash extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 提现方式
     */
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
