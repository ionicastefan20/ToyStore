package com.toy_store.financial;

public class Currency {
    private String name;
    private String symbol;
    private double parityToEur;

    public Currency() {
        this("Euro", "EUR", 1.0);
    }

    public Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    void updateParity(double parityToEUR) {
        this.parityToEur = parityToEUR;
    }
}
