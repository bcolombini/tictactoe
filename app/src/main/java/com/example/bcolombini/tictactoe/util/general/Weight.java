package com.example.bcolombini.tictactoe.util.general;

public class Weight {

    String method = null;

    int count = 0;

    public Weight(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
