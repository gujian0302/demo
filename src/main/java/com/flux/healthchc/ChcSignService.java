package com.flux.healthchc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.flux.healthchc.data.ChcData;
import com.flux.healthchc.properties.HealthChcProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ChcSignService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HealthChcProperty chcProperty;

    private URLCodec codec = new URLCodec();

    public String sign(Object object) {
        Map<String, String> map = objectMapper.convertValue(object, TreeMap.class);

        String toEncoded = map.entrySet().stream()
                .filter(e -> e.getValue() != null).filter(e -> !e.getKey().equalsIgnoreCase("signature") && !e.getKey().equalsIgnoreCase("sign")).filter(e -> !e.getKey().equals("item_orders_info"))
                .map(e -> e.getKey() + "=" + String.valueOf(e.getValue())).collect(Collectors.joining("&"));

        log.info("chc to encode:{}{}", toEncoded, chcProperty.getSecretKey());

        return DigestUtils.md5Hex(toEncoded + chcProperty.getSecretKey());
    }

    public Object createChcCreatePay(ChcData chcData) {
        chcData.setSign(this.sign(chcData));
        return this.url(chcData);
    }


    public String url(Object data) {
        Map<String, Object> map = objectMapper.convertValue(data, TreeMap.class);
        String queryParam = map.entrySet().stream()
                .filter(e -> e.getValue() != null).map(e -> {
                            try {
                                return e.getKey() + "=" + codec.encode(e.getValue().toString());
                            } catch (EncoderException e1) {
                                e1.printStackTrace();
                                return "";
                            }
                        }
                ).collect(Collectors.joining("&"));
        return chcProperty.getGateway() + "/" + queryParam;

    }

}
