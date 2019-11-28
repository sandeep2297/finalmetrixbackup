package com.metrix.ruleengine.ruleInitiator;

/*
 This class is to find correct arithmetic expression evaluate L.H.S and R.H.S
*/
public class Arithmetic {
    public boolean greaterThan(Integer userValue, int value) {
        if (userValue > value) return true;
        return false;
    }

    public boolean greaterThanEquals(Integer userValue, int value) {
        if (userValue >= value) return true;
        return false;
    }

    public boolean lessThan(Integer userValue, int value) {
        if (userValue < value) return true;
        return false;
    }

    public boolean lessThanEquals(Integer userValue, int value) {
        if (userValue <= value) return true;
        return false;
    }

    public boolean equals(String userValue, String value) {
        if (userValue.equals(value)) return true;
        return false;
    }
}
