package com.flux.healthchc.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChcCreatePayData extends ChcData {


    @JsonProperty(value = "service")
    @Override
    public String getService() {
        return "create_pay";
    }
}
