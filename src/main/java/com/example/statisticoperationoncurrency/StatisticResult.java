package com.example.statisticoperationoncurrency;

import java.util.HashMap;
import java.util.Map;

public class StatisticResult {

    private Map<String, Double> averageResult = new HashMap<>();
    private Map<String, Double> standardDeviationResult = new HashMap<>();

    public Map<String, Double> getAverageResult() {
        return averageResult;
    }

    public void setAverageResult(Map<String, Double> averageResult) {
        this.averageResult = averageResult;
    }

    public Map<String, Double> getStandardDeviationResult() {
        return standardDeviationResult;
    }

    public void setStandardDeviationResult(Map<String, Double> standardDeviationResult) {
        this.standardDeviationResult = standardDeviationResult;
    }
}
