package com.example.bcolombini.tictactoe.util.configuration;

import com.example.bcolombini.tictactoe.util.general.Weight;

import java.util.ArrayList;

public class WeightCalculate {

    ArrayList<String> computerHistory;
    ArrayList<String> humanHistory;
    PositionFinish positionFinish;

    public WeightCalculate(ArrayList<String> computerHistory, ArrayList<String> humanHistory) {
        this.computerHistory = computerHistory;
        this.humanHistory = humanHistory;
    }

    public void setPositionFinish(PositionFinish positionFinish) {
        this.positionFinish = positionFinish;
    }

    public void calculateWeight(boolean isComputer) {
        ArrayList<String> history = isComputer ? computerHistory : humanHistory;
        int sum = isComputer ? -1 : 1;
        for (Weight weight : positionFinish.getWinArray()) {
            for (String cp : history) {
                if (weight.getMethod().contains(cp)) {
                    weight.setCount(weight.getCount() + sum);
                }
            }
        }
        if (!isComputer) calculateWeight(true);
    }

    public String getBetterWay(boolean isImpossible) {
        Weight betterWay = null;
        for (Weight weight : positionFinish.getWinArray()) {
            if (betterWay == null) {
                betterWay = weight;
            }
            if (weight.getCount() > 1) {
                betterWay = weight;
            } else if (weight.getCount() == -1 && betterWay.getCount() != 2 && betterWay.getCount() != -1 && isImpossible) {
                betterWay = weight;
            } else if (weight.getCount() == -2) {
                betterWay = weight;
                break;
            }

        }
        return betterWay.getMethod();
    }
}
