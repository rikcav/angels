package com.angels.gateway.controller.response;

import com.angels.gateway.domain.ModelRisk;
import lombok.Getter;

import java.util.List;

@Getter
public class PredictResponse {

    private final boolean risk;

    private final List<ModelRisk> models;

    public PredictResponse(List<ModelRisk> models) {
        this.risk = !models.isEmpty();
        this.models = models;
    }
}
