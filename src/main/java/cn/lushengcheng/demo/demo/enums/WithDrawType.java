package cn.lushengcheng.demo.demo.enums;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 提取类型枚举值
 */
public enum WithDrawType {
    /**
     * 支付宝
     */
    ALIPAY(0, "支付宝"),
    /**
     * 微信
     */
    WECHAT(1, "微信"),
    /**
     * 银行
     */
    BANK(2, "银行"),;
    private final String value;
    private final int code;

    WithDrawType(int code, String value) {
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
