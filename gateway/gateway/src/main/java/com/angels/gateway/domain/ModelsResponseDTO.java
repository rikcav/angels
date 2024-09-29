package com.angels.gateway.domain;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ModelsResponseDTO {
    private final Map<String, Map> responseModels;
    private final Map<String, Object> modelsRiskDTO;
}
