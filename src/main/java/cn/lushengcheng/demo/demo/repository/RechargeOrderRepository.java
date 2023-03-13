package cn.lushengcheng.demo.demo.repository;

import cn.lushengcheng.demo.demo.model.RechargeOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public interface RechargeOrderRepository extends JpaRepository<RechargeOrder, Long> {


    boolean existsByOrderId(Long orderId);


    List<RechargeOrder> findAllByAccountId(Long accountId, PageRequest pageable);
}
