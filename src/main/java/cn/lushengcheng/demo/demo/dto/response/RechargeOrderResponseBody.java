package cn.lushengcheng.demo.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 充值订单
 */

@Data
@Builder
public class RechargeOrderResponseBody {

    private Long id;
    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 充值渠道
     * */
    private String channel;

    /**
     * 充值状态
     */
    private Boolean status;

    /**
     * 充值时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date createTime;

}
