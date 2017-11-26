package com.flux.healthchc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flux.healthchc.data.ChcData;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ChcMap {
    private ChcData chcData;
    private String secretKey;
    private Map<String, Object> map;

    public ChcMap(ChcData chcData, ObjectMapper objectMapper, String secretKey) {
        this.map = objectMapper.convertValue(chcData, TreeMap.class);
        this.chcData = chcData;
        this.secretKey = secretKey;
    }

    //签名
    //记住要去掉item_orders_info
    private void sign() {
        String toEncoded = map.entrySet().stream()
                .filter(e -> e.getValue() != null).filter(e -> !e.getKey().equalsIgnoreCase("signature") && !e.getKey().equalsIgnoreCase("sign")).filter(e -> !e.getKey().equals("item_orders_info"))
                .map(e -> e.getKey() + "=" + String.valueOf(e.getValue())).collect(Collectors.joining("&"));


        this.chcData.setSign(DigestUtils.md5Hex(toEncoded + this.secretKey));
        map.put("sign", this.chcData.getSign());
    }

    //获取支付页面
    public String url(String gateWay) {
        this.sign();
        if (gateWay.charAt(gateWay.length() - 1) == '?') {
            return gateWay + this.completeQueryParam();
        }
        return gateWay + "?" + this.completeQueryParam();
    }

    private String completeQueryParam() {
        return map.entrySet().stream()
                .filter(e -> e.getValue() != null).map(e -> {
                            try {
                                return e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), "utf-8");
                            } catch (UnsupportedEncodingException e1) {
                                e1.printStackTrace();
                                return "";
                            }
                        }
                ).collect(Collectors.joining("&"));
    }
}
