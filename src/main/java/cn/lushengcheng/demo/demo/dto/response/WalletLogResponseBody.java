package cn.lushengcheng.demo.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lushengcheng
 * @version 1.0.0
 * @Description 钱包流水
 */
@Data
@Builder
public class WalletLogResponseBody  {

    private Long id;
    /**
     * 流水号
     */
    private String serialNumber;

    /**
     * 业务类型
     */
    private String targetType;

    /**
     * 操作类型
     */
    private String actionType;

    /**
     * 操作金额
     */
    private BigDecimal amount;

    /**
     * 操作时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date createTime;

}
