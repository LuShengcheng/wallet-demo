package cn.lushengcheng.demo.demo.repository;

import cn.lushengcheng.demo.demo.model.WalletLog;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public interface WalletLogRepository extends JpaRepository<WalletLog, Long> {
    List<WalletLog> findAllByAccountId(Long accountId, PageRequest pageRequest);
}
