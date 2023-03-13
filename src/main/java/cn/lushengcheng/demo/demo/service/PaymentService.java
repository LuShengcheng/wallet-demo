package cn.lushengcheng.demo.demo.service;

import cn.lushengcheng.demo.demo.dto.JsonResult;
import cn.lushengcheng.demo.demo.dto.request.PaymentRequestBody;
import cn.lushengcheng.demo.demo.dto.response.PaymentResponseBody;
import cn.lushengcheng.demo.demo.dto.request.RefundRequestBody;
import cn.lushengcheng.demo.demo.enums.ActionType;
import cn.lushengcheng.demo.demo.enums.BusinessError;
import cn.lushengcheng.demo.demo.enums.PaymentStatus;
import cn.lushengcheng.demo.demo.enums.TargetType;
import cn.lushengcheng.demo.demo.exception.BusinessException;
import cn.lushengcheng.demo.demo.model.Account;
import cn.lushengcheng.demo.demo.model.Payment;
import cn.lushengcheng.demo.demo.records.WalletLogRecord;
import cn.lushengcheng.demo.demo.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */

@Service
@AllArgsConstructor
@Log4j2
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountService accountService;
    private final WalletLogService walletLogService;

    public void pay(PaymentRequestBody paymentRequestBody) {
        log.info("paymentRequestBody:{}", paymentRequestBody);
        Payment payment = Payment.builder()
                .paymentOrderNo(paymentRequestBody.getPaymentOrderNo())
                .paymentAccountId(paymentRequestBody.getPaymentAccountId())
                .paymentType(paymentRequestBody.getPaymentType())
                .paymentAmount(paymentRequestBody.getPaymentAmount()).build();

        //判断是否为钱包余额支付
        String walletType = "WALLET";
        if (walletType.equals(paymentRequestBody.getPaymentType().name())) {

            log.info("-------------------获取钱包余额------------------");
            Account account = accountService.findAccountById(paymentRequestBody.getPaymentAccountId());

            if (biggerThan(account.getBalanceFee(), paymentRequestBody.getPaymentAmount())) {

                log.info("-------------------使用钱包余额支付------------------");
                log.info("-------------------模拟流水号------------------");
                String serialNumber = UUID.randomUUID().toString().replace("-", "");
                payment.setSerialNumber(serialNumber);
                payment.setPaymentStatus(PaymentStatus.PAID);
                payment.setHasRefund(false);
                try {
                    paymentRepository.save(payment);
                } catch (Exception e) {
                    throw new BusinessException(BusinessError.ORDER_IS_EXIST);
                }

                account.setBalanceFee(account.getBalanceFee().subtract(paymentRequestBody.getPaymentAmount()));
                accountService.save(account);
                log.info("-------------------记录钱包流水------------------");
                WalletLogRecord walletLogRecord = new WalletLogRecord(
                        paymentRequestBody.getPaymentAccountId(),
                        serialNumber,
                        ActionType.ORDER,
                        paymentRequestBody.getPaymentType(),
                        paymentRequestBody.getPaymentAmount());
                walletLogService.save(walletLogRecord);
            } else {
                throw new BusinessException(BusinessError.BALANCE_FEE_IS_NOT_ENOUGH);
            }
        } else {
            log.info("-------------------获取第三支付状态------------------");
            log.info("-------------------保存三支付状态------------------");
            payment.setPaymentStatus(PaymentStatus.PAID);
            paymentRepository.save(payment);
        }
    }

    /**
     * 判断原始金额是否大于目标金额
     *
     * @param original     原始金额
     * @param targetAmount 目标金额
     * @return boolean
     */
    private boolean biggerThan(BigDecimal original, BigDecimal targetAmount) {
        return original.compareTo(targetAmount) > -1;
    }

    public void refund(RefundRequestBody refundRequestBody) {

        Long paymentOrderNo = refundRequestBody.getPaymentOrderNo();
        Payment paymentByOrderNo = findPaymentByOrderNo(paymentOrderNo);

        //判断订单金额是否大于等于退款金额
        boolean biggerThan = biggerThan(paymentByOrderNo.getPaymentAmount(), refundRequestBody.getRefundAmount());

        if (biggerThan && paymentByOrderNo.getPaymentStatus().name().equals(PaymentStatus.PAID.name())) {
            //如果是第钱包支付退款到钱包
            if (canRefund2Wallet(paymentByOrderNo)) {
                if (paymentByOrderNo.getHasRefund()) {
                    log.info("-------------------------异常----------");
                    throw new BusinessException(BusinessError.HAS_REFUND);
                }
                log.info("-------------------退款到钱包------------------");
                Account account = accountService.findAccountById(paymentByOrderNo.getPaymentAccountId());
                account.setBalanceFee(account.getBalanceFee().add(refundRequestBody.getRefundAmount()));
                accountService.save(account);
                log.info("-------------------记录钱包流水------------------");
                WalletLogRecord walletLogRecord = new WalletLogRecord(
                        paymentByOrderNo.getPaymentAccountId(),
                        paymentByOrderNo.getSerialNumber(),
                        ActionType.REFUNDED,
                        paymentByOrderNo.getPaymentType(),
                        refundRequestBody.getRefundAmount());
                walletLogService.save(walletLogRecord);
            } else {
                log.info("-------------------退款到第三方------------------");
            }
            paymentByOrderNo.setPaymentStatus(PaymentStatus.REFUNDED);
            paymentByOrderNo.setHasRefund(true);
            paymentByOrderNo.setRefundAmount(refundRequestBody.getRefundAmount());
            paymentByOrderNo.setRemark(
                    "备注:" + refundRequestBody.getRefundRemark() +
                            "退款原因:" + refundRequestBody.getRefundReason());
            paymentRepository.save(paymentByOrderNo);
        } else {
            if (!biggerThan) {
                throw new BusinessException(BusinessError.REFUND_AMOUNT_IS_MORE_THAN_PAYMENT_AMOUNT);
            } else {
                throw new BusinessException(BusinessError.HAS_REFUND);
            }
        }
    }

    public Payment findPaymentByOrderNo(Long paymentOrderNo) {
        return paymentRepository
                .findByPaymentOrderNo(paymentOrderNo)
                .orElseThrow(() -> new BusinessException(BusinessError.PAYMENT_NOT_FOUND));
    }

    private boolean canRefund2Wallet(Payment payment) {
        log.info("payment type {}", payment.getPaymentType().ordinal());
        log.info("TargetType {}", TargetType.WALLET.ordinal());
        return Objects.equals(payment.getPaymentType().getValue(), TargetType.WALLET.getValue());

    }

    public JsonResult query(Long accountId, Integer page, Integer size) {
        List<PaymentResponseBody> paymentResponseBodies = paymentRepository.
                findAllByPaymentAccountId(accountId, PageRequest.of(page - 1, size))
                .orElseThrow(() -> {
                    throw new BusinessException(BusinessError.PAYMENT_NOT_FOUND);
                })
                .stream()
                .map(this::mapToPaymentResponseBody)
                .toList();
        return JsonResult.success("查询成功！", paymentResponseBodies, paymentResponseBodies.size());
    }

    private PaymentResponseBody mapToPaymentResponseBody(Payment payment) {
        return PaymentResponseBody.builder()
                .paymentId(payment.getPaymentId())
                .serialNumber(payment.getSerialNumber())
                .paymentOrderNo(payment.getPaymentOrderNo())
                .paymentAccountId(payment.getPaymentAccountId())
                .paymentType(payment.getPaymentType())
                .paymentAmount(payment.getPaymentAmount())
                .paymentStatus(payment.getPaymentStatus())
                .hasRefund(payment.getHasRefund())
                .refundAmount(payment.getRefundAmount())
                .remark(payment.getRemark())
                .createTime(payment.getCreateTime())
                .build();
    }

}
