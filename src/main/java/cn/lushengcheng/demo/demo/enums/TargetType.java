package cn.lushengcheng.demo.demo.enums;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description
 */
public enum TargetType {

    /**
     * 支付宝
     */
    ALIPAY(0,"支付宝"),
    /**
     * 微信
     */
    WECHAT(1,"微信"),
    /**
     * 银行
     */
    BANK(2,"银行"),
    /**
     * 钱包支付
     */
    WALLET(3,"钱包");

    private final int code;
    private final String value;

    TargetType(int code,String value) {
        this.code = code;
        this.value = value;
    }
    public int getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

}
