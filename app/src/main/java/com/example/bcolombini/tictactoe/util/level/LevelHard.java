package com.example.bcolombini.tictactoe.util.level;

import android.text.TextUtils;
import android.widget.Button;

import java.util.HashMap;

public class LevelHard extends Level{


    public LevelHard(HashMap<String, Button> boardGame) {
        super(boardGame);
    }

    @Override
    public HashMap<String, Integer> getMove() {
        HashMap<String, Integer> position = new HashMap<>();

        if (!getBoardGame().containsValue("o")) {
            if (TextUtils.isEmpty(getBoardGame().get("1-1"))) {
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
