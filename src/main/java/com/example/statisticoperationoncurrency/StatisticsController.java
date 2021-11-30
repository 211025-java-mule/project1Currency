package com.example.statisticoperationoncurrency;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

import static com.example.statisticoperationoncurrency.Statistics.averageCurrencyValueBasedOnEUR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/currency", produces = APPLICATION_JSON_VALUE)
public class StatisticsController {

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(value = "/calculate", consumes = APPLICATION_JSON_VALUE)
    public String calculate(@RequestBody CurrencyData currencyData) throws JsonProcessingException {
        Statistics statistics = new Statistics();

        Map<String, Double> averageRate = statistics
                .getAverageCurrencyValue(averageCurrencyValueBasedOnEUR(currencyData.getCurrencyList()));

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


        return objectMapper.writeValueAsString(averageRate);
    }

    public String convertWithStream(Map<String, Double> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

}
