package cn.lushengcheng.demo.demo.repository;

import cn.lushengcheng.demo.demo.model.WithdrawCash;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public interface WithdrawCashRepository extends JpaRepository<WithdrawCash,Long> {
    Optional<List<WithdrawCash>> findAllByAccountId(Long accountId, PageRequest pageRequest);
}
