package com.flux.healthchc.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncNotifyData {

    //成功标识
    @JsonProperty(value = "is_success")
    private String success;
    //签名方式
    @JsonProperty(value = "sign_type")
    private String signType;
    //签名
    @JsonProperty(value = "sign")
    private String sign;
    //唯一交易号
    @JsonProperty(value = "out_trade_no")
    private String orderId;
    //支付类型
    @JsonProperty(value = "payment_type")
    private String paymentType;
    //接口名称
    @JsonProperty(value = "exterface")
    private String exterface;
    //健保通付交易号
    @JsonProperty(value = "trade_no")
    private String chcId;
    //交易状态
    @JsonProperty(value = "trade_status")
    private String tradeStatus;
    //通知校验ID
    @JsonProperty(value = "notify_id")
    private String notifyId;
    //通知时间
    @JsonProperty(value = "notify_time")
    private Date notifyTime;
    //通知类型
    @JsonProperty(value = "notify_type")
    private String notifyType;

    //卖家帐号
    @JsonProperty(value = "seller_account")
    private String sellerAccount;
    //卖家帐号
    @JsonProperty(value = "buyer_account")
    private String buyerAccount;
    //卖家帐号
    @JsonProperty(value = "total_fee")
    private BigDecimal totalFee;
    //公用回传参数
    @JsonProperty(value = "extra_params")
    private String extraParams;

}
