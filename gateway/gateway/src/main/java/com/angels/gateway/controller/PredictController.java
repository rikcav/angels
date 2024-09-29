package com.angels.gateway.controller;

import com.angels.gateway.controller.response.PredictResponse;
import com.angels.gateway.domain.ModelRisk;
import com.angels.gateway.domain.ParametersExample;
import com.angels.gateway.service.PredictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/predict")
@RequiredArgsConstructor
public class PredictController {

    private final PredictService service;

    @PostMapping
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ParametersExample.class))))
    public ResponseEntity<PredictResponse> multiPredicts(@RequestBody Map<String, ?> parameters) {
        return ResponseEntity.ok(new PredictResponse(service.MultiPredicts(parameters)));
    }
}
