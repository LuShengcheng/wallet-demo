package cn.lushengcheng.demo.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lushengcheng
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private BigDecimal balanceFee;


}
