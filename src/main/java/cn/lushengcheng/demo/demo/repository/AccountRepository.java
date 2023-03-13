package cn.lushengcheng.demo.demo.repository;

import cn.lushengcheng.demo.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lushengcheng
 * @version 1.0.0
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
