package com.angels.gateway.service;

import com.angels.gateway.domain.ModelRisk;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictService {

    private final EurekaClient eurekaClient;

    private final ModelsService modelsService;

//    public List<ModelRisk> MultiPredicts(Map<String, ?> parameters) {
//        List<String> models =  eurekaClient.getApplications().getRegisteredApplications().stream()
//                .map(application -> application.getName().toLowerCase())
//                .filter(name -> name.startsWith("model")).toList();
//        List<ModelRisk> responseModels = new ArrayList<>();
//        List<Boolean> check = models.parallelStream().map(model -> toCheck(responseModels, model, parameters)).toList();
//        return responseModels;
//    }

    public List<ModelRisk> MultiPredicts(Map<String, ?> parameters) {
        // Agrupar modelos por nome base e ordenar versões em ordem decrescente
        Map<String, List<String>> groupedModels = eurekaClient.getApplications().getRegisteredApplications().stream()
                .map(application -> application.getName().toLowerCase())
                .filter(name -> name.startsWith("model"))
                .collect(Collectors.groupingBy(this::extractBaseName, Collectors.toList()));

        // Ordenar versões em ordem decrescente
        groupedModels.forEach((baseName, versions) ->
                versions.sort((v1, v2) -> extractVersion(v2) - extractVersion(v1)));

        List<ModelRisk> responseModels = new ArrayList<>();
        groupedModels.values().forEach(versions -> checkModelsByVersion(responseModels, versions, parameters));

        return responseModels;
    }

    private String extractBaseName(String modelName) {
        int lastDashIndex = modelName.lastIndexOf('-');
        return lastDashIndex == -1 ? modelName : modelName.substring(0, lastDashIndex);
    }

    private int extractVersion(String modelName) {
        int lastDashIndex = modelName.lastIndexOf('-');
        if (lastDashIndex != -1 && modelName.substring(lastDashIndex + 1).startsWith("version")) {
            return Integer.parseInt(modelName.substring(lastDashIndex + 8));
        }
        return 1; // Default version if none specified
    }

    private void checkModelsByVersion(List<ModelRisk> responseModels, List<String> versions, Map<String, ?> parameters) {
        for (String version : versions) {
            if (toCheck(responseModels, version, parameters)) {
                break; // Parar de verificar se uma versão superior atender aos requisitos
            }
        }
    }

//    private Map<String, ?> requestBuild(Map<String, ?> parameters, List<String> parametersModel) {
//        Map<String, Object> request = new HashMap<>();
//        for (String parameter: parametersModel) {
//            request.putIfAbsent(parameter, parameters.get(parameter));
//        }
//        return request;
//    }

    private Map<String, ?> requestBuild(Map<String, ?> parameters, List<String> parametersModel) {
        Map<String, Object> request = new HashMap<>();
        for (String parameter : parametersModel) {
            request.putIfAbsent(parameter, parameters.get(parameter));
        }
        return request;
    }


//    private boolean toCheck(List<ModelRisk> responseModels, String model, Map<String, ?> parameters) {
//        List<String> parametersModel = modelsService.parametersModel(model);
//        if (parametersModel.stream().allMatch(parameters::containsKey)) {
//            Map<String, ?> request = requestBuild(parameters, parametersModel);
//            ModelRisk modelRisk = modelsService.modelPredict(model, request);
//            if (modelRisk.getPrediction() == 1) {
//                modelRisk.setModel(model);
//                responseModels.add(modelRisk);
//            }
//        }
//        return true;
//    }

    private boolean toCheck(List<ModelRisk> responseModels, String model, Map<String, ?> parameters) {
        List<String> parametersModel = modelsService.parametersModel(model);
        if (parametersModel.stream().allMatch(parameters::containsKey)) {
            Map<String, ?> request = requestBuild(parameters, parametersModel);
            ModelRisk modelRisk = modelsService.modelPredict(model, request);
            //if (modelRisk.getPrediction() == 1) {
            modelRisk.setModel(model);
            responseModels.add(modelRisk);
            return true; // Retornar true se o modelo atender aos requisitos
            //}
        }
        return false; // Retornar false se o modelo não atender aos requisitos
    }

}
