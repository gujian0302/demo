package com.flux;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flux.healthchc.*;
import com.flux.healthchc.data.ChcCreatePayData;
import com.flux.healthchc.data.ChcOrderItem;
import com.flux.healthchc.data.ChcScanPayData;
import com.flux.healthchc.properties.HealthChcProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.net.URLCodec;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Autowired
    private ChcSignService chcSignService;

    @Autowired
    private HealthChcProperty chcProperty;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private URLCodec codec = new URLCodec();

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSign() {
        ChcCreatePayData data = new ChcCreatePayData();
        data.setSubject("医疗包");
        data.setPartner(chcProperty.getPartner());
        data.setNotifyUrl("http://www.baidu.com");
        data.setOrderId("12342226");
        data.setTotalFee(new BigDecimal(100.00).setScale(2, BigDecimal.ROUND_HALF_UP));

        ChcOrderItem chcOrderItem = ChcOrderItem.builder().goods("医疗包").num(1).price(new BigDecimal(2).setScale(2, BigDecimal.ROUND_HALF_UP)).build();
        try {
            data.setItemOrdersInfo(objectMapper.writeValueAsString(Lists.newArrayList(chcOrderItem)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ChcMap chcMap = new ChcMap(data, objectMapper, chcProperty.getSecretKey());
        log.info(chcMap.url(chcProperty.getGateway()));

    }


    @Test
    public void testScanPay() throws JsonProcessingException {
        ChcScanPayData chcScanPayData = new ChcScanPayData();
        chcScanPayData.setSubject("医疗包");
        chcScanPayData.setPartner(chcProperty.getPartner());
        chcScanPayData.setNotifyUrl("http://www.baidu.com");
        chcScanPayData.setOrderId("1234567");
        chcScanPayData.setTotalFee(new BigDecimal(100.00).setScale(2, BigDecimal.ROUND_HALF_UP));

        chcScanPayData.setPayCode("123456");
        chcScanPayData.setThirdKey("优医家hello world");
        chcScanPayData.setThirdName("陆家嘴金融分部");

        ChcOrderItem chcOrderItem = ChcOrderItem.builder().goods("医疗包").num(1).price(new BigDecimal(2).setScale(2, BigDecimal.ROUND_HALF_UP)).build();
        chcScanPayData.setItemOrdersInfo(objectMapper.writeValueAsString(Lists.newArrayList(chcOrderItem)));

        ChcMap chcMap = new ChcMap(chcScanPayData, objectMapper, chcProperty.getSecretKey());
        log.info(chcMap.url(chcProperty.getGateway()));

//        chcScanPayData.setSign(chcSignService.sign(chcScanPayData));
//
//        Map<String, Object> map = objectMapper.convertValue(chcScanPayData, TreeMap.class);
//
//        String queryParam = map.entrySet().stream()
//                .filter(e -> e.getValue() != null).map(e -> {
//                            try {
//                                Object tmp = e.getValue().toString();
//                                return e.getKey() + "=" + codec.encode(e.getValue().toString());
//                            } catch (EncoderException e1) {
//                                e1.printStackTrace();
//                                return "";
//                            }
//                        }
//                ).collect(Collectors.joining("&"));
//
//        log.info("query:{}?{}", chcProperty.getGateway(), queryParam);
    }


}
