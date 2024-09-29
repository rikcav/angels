package com.angels.gateway.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelRisk {

    private String model;

    private int prediction;

    private Object probability;

}
