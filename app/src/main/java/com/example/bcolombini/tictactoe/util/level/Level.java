package com.example.bcolombini.tictactoe.util.level;

import android.text.TextUtils;
import android.widget.Button;

import com.example.bcolombini.tictactoe.util.configuration.PositionFinish;
import com.example.bcolombini.tictactoe.util.configuration.WeightCalculate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Level {

    PositionFinish positionFinish;

    WeightCalculate weightCalculate;

    private ArrayList<String> computerHistory = new ArrayList<>();

    private ArrayList<String> humanHistory = new ArrayList<>();

    private HashMap<String, String> boardGame = new HashMap<>();

    public HashMap<String, String> getBoardGame() {
        return boardGame;
    }

    Level(HashMap<String, Button> boardGame) {
        setBoard(boardGame);
        weightCalculate = new WeightCalculate(computerHistory,humanHistory);
    }

    public abstract HashMap<String, Integer> getMove();

    public HashMap<String, Integer> getPosition(String method) {
        HashMap<String, Integer> position = new HashMap<>();
        String[] splited = method.split(" - ");
        for (String sp : splited) {
            if (TextUtils.isEmpty(boardGame.get(sp))) {
                position.put("x", Integer.parseInt(sp.split("-")[0]));
                position.put("y", Integer.parseInt(sp.split("-")[1]));
                return position;
            }
        }
        return randomPosition();
    }

    HashMap<String, Integer> randomPosition() {
        HashMap<String, Integer> position = new HashMap<>();
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        position.put("x", x);
        position.put("y", y);
        return position;
    }


    public void setBoard(HashMap<String, Button> board) {
        boardGame = new HashMap<>();
        humanHistory = new ArrayList<>();
        for (String key : board.keySet()) {
            if (board.get(key).getText().toString().equals("x")) {
                humanHistory.add(key);
            } else if (board.get(key).getText().toString().equals("o")) {
                computerHistory.add(key);
            }
            boardGame.put(key, board.get(key).getText().toString());
        }

        positionFinish = new PositionFinish(boardGame);
    }

    public void clearHistory() {
        computerHistory = new ArrayList<>();
        humanHistory = new ArrayList<>();
    }



}
