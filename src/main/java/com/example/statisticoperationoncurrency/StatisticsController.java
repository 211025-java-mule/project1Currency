package com.example.statisticoperationoncurrency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.example.statisticoperationoncurrency.StatisticalMethods.averageCurrencyValueBasedOnEUR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/currency", produces = APPLICATION_JSON_VALUE)
public class StatisticsController {

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping(value = "/getAverageCalculation", consumes = APPLICATION_JSON_VALUE)
    public StatisticResult calculate(@RequestBody CurrencyData currencyData) throws JsonProcessingException {
        StatisticalMethods statistics = new StatisticalMethods();
        StatisticResult stastisticResult = new StatisticResult();

        Map<String, Double> averageRate = statistics
                .getAverageCurrencyValue(averageCurrencyValueBasedOnEUR(currencyData.getCurrencyList()));
        stastisticResult.setAverageResult(averageRate);


        return stastisticResult;
    }

    @PostMapping(value = "/getStandardDeviationCalculation", consumes = APPLICATION_JSON_VALUE)
    public StatisticResult calculateStandardDeviation(@RequestBody CurrencyData currencyData) throws JsonProcessingException {
        StatisticalMethods statistics = new StatisticalMethods();
        StatisticResult stastisticResult = new StatisticResult();

        Map<String, Double> standardDeviationOfRate = statistics
                .getStandardDeviationCurrencyValue(averageCurrencyValueBasedOnEUR(currencyData.getCurrencyList()));
        stastisticResult.setStandardDeviationResult(standardDeviationOfRate);


        return stastisticResult;
    }

}
