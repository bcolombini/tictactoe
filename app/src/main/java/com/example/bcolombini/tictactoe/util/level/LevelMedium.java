package com.example.bcolombini.tictactoe.util.level;

import android.widget.Button;

import java.util.HashMap;

public class LevelMedium extends Level {

    public LevelMedium(HashMap<String, Button> boardGame) {
        super(boardGame);
    }

    @Override
    public HashMap<String, Integer> getMove() {
        HashMap<String, Integer> position = new HashMap<>();

        if (!getBoardGame().containsValue("o")) {
            if (getBoardGame().get("0-0").equals("x") || getBoardGame().get("0-2").equals("x") || getBoardGame().get("2-0").equals("x") || getBoardGame().get("2-2").equals("x")) {
                position.put("x", 1);
                position.put("y", 1);
                return position;
            }
        }


        weightCalculate.setPositionFinish(positionFinish);
        weightCalculate.calculateWeight(false);

        return getPosition(weightCalculate.getBetterWay());

    }
}
