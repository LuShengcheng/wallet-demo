package cn.lushengcheng.demo.demo.repository;

import cn.lushengcheng.demo.demo.model.Account;
import cn.lushengcheng.demo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<Account> {

}
