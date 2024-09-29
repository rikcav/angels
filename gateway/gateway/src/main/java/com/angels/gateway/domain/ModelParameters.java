package com.angels.gateway.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModelParameters {

    private List<String> parameters;

    public ModelParameters() {}

    public ModelParameters(List<String> parameters) {
        this.parameters = parameters;
    }

}
