package com.example.statisticoperationoncurrency;

import java.util.*;

public class Statistics {

    public static Map<String, List<Double>> averageCurrencyValueBasedOnEUR(List<Currency> currency) {
        Map<String, List<Double>> value = new HashMap<>();

        for (int i = 0; i < currency.size(); i++) {
            for (String key : currency.get(i).getRates().keySet()) {
                if (value.containsKey(key)) {
                    value.get(key).add(currency.get(i).getRates().get(key));
                }else{
                    List<Double> array = new ArrayList<>();
                    array.add(currency.get(i).getRates().get(key));
                    value.put(key, array );
                }
            }
        }
        return value;
    }

    private double calculateAverage(List<Double> currencyValue) {
        return currencyValue.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    public Map<String, Double> getAverageCurrencyValue(Map<String, List<Double>> value) {
        Map<String, Double> currencyAverageMap = new HashMap<>();
        for (String key : value.keySet()) {
            currencyAverageMap.put(key, calculateAverage(value.get(key)));
        }
        return currencyAverageMap;
    }
    public Map<String, Double> getStandardDeviationCurrencyValue(Map<String, List<Double>> value) {
        Map<String, Double> currencyStandardDeviationMap = new HashMap<>();

        for (String key : value.keySet()) {
            double standardDeviation = 0.0;
            int listSize = value.get(key).size();
            double mean = calculateAverage(value.get(key));
            for(int i=0;i<listSize;i++){
                standardDeviation += Math.pow(value.get(key).get(i) - mean, 2);
            }
            currencyStandardDeviationMap.put(key, Math.sqrt(standardDeviation/listSize));
        }
        return currencyStandardDeviationMap;
    }

}
