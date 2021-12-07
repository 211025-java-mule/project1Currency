package com.example.statisticoperationoncurrency;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticalMethods {

    public static Map<String, List<Double>> averageCurrencyValueBasedOnEUR(List<Currency> currency) {
        Map<String, List<Double>> value = new HashMap<>();

        currency.stream().forEach((obj)-> {
            obj.getRates().keySet()
                    .stream().forEach((key)->{
                        if(value.containsKey(key)){
                            value.get(key).add(obj.getRates().get(key));
                        }else{
                            value.put(key, Stream.of(obj.getRates().get(key)).collect(Collectors.toList()));
                        }
                    });
        });

        return value;

    }

    private double calculateAverage(List<Double> currencyValue) {
        return currencyValue.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    public Map<String, Double> getAverageCurrencyValue(Map<String, List<Double>> value) {

        return value.keySet().stream()
                .collect(Collectors.toMap((key)->key,(key)->calculateAverage(value.get(key))));
    }

    public Map<String, Double> getStandardDeviationCurrencyValue(Map<String, List<Double>> value) {
        Map<String, Double> currencyStandardDeviationMap = new HashMap<>();

        value.keySet().stream().forEach((key)-> {
            double standardDeviation = 0.0;
            double meanValue = calculateAverage(value.get(key));
            standardDeviation = value.get(key).stream().mapToDouble(aDouble -> Math.pow(aDouble - meanValue, 2)).sum();
            currencyStandardDeviationMap.put(key,Math.sqrt(standardDeviation));
                });
        return currencyStandardDeviationMap;
    }

}
