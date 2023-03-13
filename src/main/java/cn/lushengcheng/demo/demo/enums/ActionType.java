package cn.lushengcheng.demo.demo.enums;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public enum ActionType {
    /**
     * 充值
     */
    RECHARGE(0,"充值"),
    /**
     * 提现
     */
    WITHDRAW(1,"提现"),
    /**
     * 下单
     */
    ORDER(2,"订单支付"),
    /**
     * 退款
     */
    REFUNDED(3,"退款");

    private final int code;
    private final String value;

    ActionType(int code,String value) {
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
