package com.flux.healthchc.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class ChcData {

    @JsonProperty(value = "partner")
    private String partner;

    @JsonProperty(value = "sign")
    private String sign;

    @JsonProperty(value = "notify_url")
    private String notifyUrl;

    @JsonProperty(value = "out_trade_no")
    private String orderId;

    @JsonProperty(value = "subject" )
    private String subject;

    /**
     * 支付方式1：pc ，2：app页
     */
    @JsonProperty(value = "payment_method")
    private String paymentMethod = "2";

    /**
     * 最终交易金额
     */
    @JsonProperty(value = "total_fee")
    private BigDecimal totalFee;

    @JsonProperty(value = "item_orders_info")
    private String itemOrdersInfo;

    @JsonProperty(value = "service")
    public abstract String getService();

}
