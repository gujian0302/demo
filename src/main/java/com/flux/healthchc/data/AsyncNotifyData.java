package com.flux.healthchc.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsyncNotifyData {
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
    //退款时间
    @JsonProperty(value = "subject")
    private String subject;

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
    //商品单价
    @JsonProperty(value = "price")
    private BigDecimal price;
    //购买数量
    @JsonProperty(value = "quantity")
    private Integer quantity;

    //商品描述
    @JsonProperty(value = "body")
    private String body;

    //交易金额
    @JsonProperty(value = "total_fee")
    private BigDecimal totalFee;
    //公用回传参数
    @JsonProperty(value = "extra_params")
    private String extraParams;
    //交易创建时间
    @JsonProperty(value = "gmt_create")
    private Date gmtCreate;
    //交易关闭时间
    @JsonProperty(value = "gmt_close")
    private Date gmtClose;
    //退款时间
    @JsonProperty(value = "gmt_refund")
    private Date gmtRefund;
    //退款状态
    @JsonProperty(value = "refund_status")
    private String refundStatus;
    //交易明细
    @JsonProperty(value = "pay_list")
    private String payList;


}
