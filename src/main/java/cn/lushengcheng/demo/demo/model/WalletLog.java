package cn.lushengcheng.demo.demo.model;

import cn.lushengcheng.demo.demo.enums.ActionType;
import cn.lushengcheng.demo.demo.enums.TargetType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 钱包流水
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_wallet_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账户编号
     */
    private Long accountId;
    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * 业务类型
     */
    private TargetType targetType;

    /**
     * 操作类型
     */
    private ActionType actionType;
    /**
     * 操作金额
     */
    private BigDecimal amount;
    /**
     * 备注
     */
    private String remark;

}
