package cn.lushengcheng.demo.demo.repository;

import cn.lushengcheng.demo.demo.model.Payment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    /**
     * <p>
     *      根据订单号查询支付信息
     *</p>
     * @param paymentOrderNo
     * @return cn.lushengcheng.demo.demo.model.Payment
     * @author lsc
     * @since 2023/3/12 下午3:59
     **/
    Optional<Payment> findByPaymentOrderNo(Long paymentOrderNo);

    Optional<List<Payment>> findAllByPaymentAccountId(Long accountId, PageRequest of);
}
