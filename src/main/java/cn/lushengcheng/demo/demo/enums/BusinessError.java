package cn.lushengcheng.demo.demo.enums;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */

public enum BusinessError {
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(10001, "用户不存在"),
    /**
     * 账户不存在
     */
    ACCOUNT_NOT_EXIST(10002, "账号不存在"),
    /**
     * 账户余额不足
     */
    BALANCE_FEE_IS_NOT_ENOUGH(10003, "余额不足"),
    /**
     * 枚举值不在范围内
     */
    ENUM_NOT_FOUND(1004, "举值不在范围内"),
    /**
     * 支付订单不存在
     */
    PAYMENT_NOT_FOUND(1005, "支付订单不存在"),
    /**
     * 退款超过原始订单金额
     */
    REFUND_AMOUNT_IS_MORE_THAN_PAYMENT_AMOUNT(1006, "退款超过原始订单金额"),
    /**
     * 订单已经退款
     */
    HAS_REFUND(1007, "订单已经退款"),
    /**
     * 订单号码已经存在
     */
    ORDER_IS_EXIST(1008, "订单号码已经存在"),
    DATA_IS_EMPTY(1009, "数据为空！");
    private final Integer code;
    private final String message;

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    BusinessError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
