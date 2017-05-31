package com.example.bcolombini.tictactoe.util;

import android.text.TextUtils;
import android.widget.Button;

import com.example.bcolombini.tictactoe.util.configuration.PositionFinish;
import com.example.bcolombini.tictactoe.util.configuration.WeightCalculate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LevelImp implements LevelInterface {

    PositionFinish positionFinish;
    WeightCalculate weightCalculate;

    private HashMap<String, Integer> position = new HashMap<>();

    private ArrayList<String> computerHistory = new ArrayList<>();

    private ArrayList<String> humanHistory = new ArrayList<>();

    private HashMap<String, String> boardGame = new HashMap<>();

    @Override
    public void choiceLevel(int level) {
        weightCalculate = new WeightCalculate(computerHistory, humanHistory);
        switch (level) {
            case 0:
                position = easy();
                break;
            case 1:
                position = medium();
                break;
            case 2:
                position = hard();
                break;
            default:
                position = easy();
        }
        computerHistory.add(position.get("x") + "-" + position.get("y"));
    }

    @Override
    public HashMap<String, Integer> getPosition() {
        return position;
    }

    @Override
    public void setBoard(HashMap<String, Button> board) {
        boardGame = new HashMap<>();
        humanHistory = new ArrayList<>();
        for (String key : board.keySet()) {
            if (board.get(key).getText().toString().equals("x")) {
                humanHistory.add(key);
            }
            boardGame.put(key, board.get(key).getText().toString());
        }

        positionFinish = new PositionFinish(boardGame);
    }

    private HashMap<String, Integer> easy() {
        HashMap<String, Integer> position = new HashMap<>();
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        position.put("x", x);
        position.put("y", y);
        return position;
    }

    //Futuro Medio
    private HashMap<String, Integer> medium() {
        HashMap<String, Integer> position = new HashMap<>();

        if (!boardGame.containsValue("o")) {
            if (boardGame.get("0-0").equals("x") || boardGame.get("0-2").equals("x") || boardGame.get("2-0").equals("x") || boardGame.get("2-2").equals("x")) {
                position.put("x", 1);
                position.put("y", 1);
                return position;
            }
        }

        weightCalculate.setPositionFinish(positionFinish);
        weightCalculate.calculateWeight(false);

        return getPosition(weightCalculate.getBetterWay(false));

    }

    private HashMap<String, Integer> hard() {
        HashMap<String, Integer> position = new HashMap<>();

        if (!boardGame.containsValue("o")) {
            if (boardGame.get("0-0").equals("x") || boardGame.get("0-2").equals("x") || boardGame.get("2-0").equals("x") || boardGame.get("2-2").equals("x")) {
                position.put("x", 1);
                position.put("y", 1);
                return position;
            }
        }

        weightCalculate.setPositionFinish(positionFinish);
        weightCalculate.calculateWeight(false);

        return getPosition(weightCalculate.getBetterWay(true));
    }

    private HashMap<String, Integer> getPosition(String method) {
        HashMap<String, Integer> position = new HashMap<>();
        String[] splited = method.split(" - ");
        for (String sp : splited) {
            if (TextUtils.isEmpty(boardGame.get(sp))) {
                position.put("x", Integer.parseInt(sp.split("-")[0]));
                position.put("y", Integer.parseInt(sp.split("-")[1]));
                return position;
            }
        }
        return easy();
    }

    public void clearHistory() {
        computerHistory = new ArrayList<>();
        humanHistory = new ArrayList<>();
    }
}
