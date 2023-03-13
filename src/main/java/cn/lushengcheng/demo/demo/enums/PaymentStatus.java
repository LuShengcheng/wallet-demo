package cn.lushengcheng.demo.demo.enums;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public enum PaymentStatus {
    /**
     * 支付完成
     */
    PAID(0,"支付完成"),
    /**
     * 未支付
     */
    UNPAID(1,"未支付"),
    /**
     * 支付失败
     */
    FAIL(2,"支付失败"),
    /**
     * 退款
     */
    REFUNDED(3,"退款");

    private final int code;
    private final String value;

    PaymentStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
