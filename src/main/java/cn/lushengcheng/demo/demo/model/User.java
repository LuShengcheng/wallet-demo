package cn.lushengcheng.demo.demo.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

}
