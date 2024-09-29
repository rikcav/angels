package com.angels.gateway.service;

import com.angels.gateway.domain.ModelRisk;
import com.angels.gateway.utils.exception.BadGatewayException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelsService {

    public static final String PROTOCOL = "https://";
    public static final String PATH_PARAMETERS = "/api/parameters";

    public static final String PATH_PREDICT = "/api/predict";

    private final WebClient.Builder webClient;

    //@Cacheable("parameters")
    public List<String> parametersModel(String application) {
        Optional<String[]> optionalModelParameters = webClient.build().get()
                .uri(PROTOCOL + application + PATH_PARAMETERS).retrieve()
                .bodyToMono(String[].class).blockOptional();
        if (optionalModelParameters.isEmpty() || optionalModelParameters.get().length == 0) {
            throw new BadGatewayException("Error receiving parameters from model " + application + ".");
        }
        return Arrays.stream(optionalModelParameters.get()).toList();
    }

    public ModelRisk modelPredict(String application, Map<String, ?> parameters) {
        Optional<ModelRisk> optionalModelPredict = webClient.build().post()
                .uri(PROTOCOL + application + PATH_PREDICT)
                .bodyValue(parameters).retrieve().bodyToMono(ModelRisk.class).blockOptional();
        if (optionalModelPredict.isEmpty()) {
            throw new BadGatewayException("Error receiving risk from model " + application + ".");
        }
        return optionalModelPredict.get();
    }

}
