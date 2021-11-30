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
        Map<String, Double> currencyAverage = new HashMap<>();
        for (String key : value.keySet()) {
            currencyAverage.put(key, calculateAverage(value.get(key)));
        }
        return currencyAverage;
    }


}
