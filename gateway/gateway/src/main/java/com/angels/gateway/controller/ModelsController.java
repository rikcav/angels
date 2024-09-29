package com.angels.gateway.controller;

import com.angels.gateway.service.ModelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelsController {

    private final ModelsService modelsService;

    @GetMapping("/{application}")
    @Deprecated
    public ResponseEntity<List<String>> parametersModel(@PathVariable String application) {
        return ResponseEntity.ok(modelsService.parametersModel(application));
    }

    @PostMapping("/{application}")
    @Deprecated
    public ResponseEntity<?> modelPredict(@PathVariable String application, @RequestBody Map<String, ?> parameters) {
        return ResponseEntity.ok(modelsService.modelPredict(application, parameters));
    }

}
