package com.flux.healthchc.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flux.healthchc.data.ChcData;
import lombok.Data;

@Data
public class ChcScanPayData extends ChcData {

    //分店Id
    @JsonProperty(value = "third_key")
    private String thirdKey;

    //分店名称
    @JsonProperty(value = "third_name")
    private String  thirdName;

    //付款码
    @JsonProperty(value = "pay_code")
    private String payCode;

    @Override
    public String getService() {
        return "scan_pay";
    }
}
