package com.example.statisticoperationoncurrency;

import java.util.List;

public class CurrencyData {
    List<Currency> currencyList;

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }
}
