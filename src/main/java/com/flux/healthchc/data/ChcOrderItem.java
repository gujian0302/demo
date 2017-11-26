package com.flux.healthchc.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * goods：商品名称、price：商品单价、num：购买数量
 */
@Data
@Builder
public class ChcOrderItem {

    private String goods;
    private BigDecimal price;
    private Integer num;

}
