package com.example.bcolombini.tictactoe.util;

import android.text.TextUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LevelImp implements LevelInterface {

    private HashMap<String, Integer> position = new HashMap<>();

    private ArrayList<String> computerHistory = new ArrayList<>();

    private ArrayList<String> humanHistory = new ArrayList<>();

    private HashMap<String, String> boardGame = new HashMap<>();

    private ArrayList<Weight> winArray;


    @Override
    public void choiceLevel(int level) {
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

    private HashMap<String, Integer> medium() {
        HashMap<String, Integer> position = new HashMap<>();
        positionToFinishGame();

        if (!boardGame.containsValue("o")) {
            return easy();
        }

        for (Weight weight : winArray) {
            for (String cp : computerHistory) {
                if (weight.getMethod().contains(cp)) {
                    weight.setCount(weight.getCount() + 1);
                }
            }
        }

        for (Weight weight : winArray) {
            for (String hp : humanHistory) {
                if (weight.getMethod().contains(hp)) {
                    weight.setCount(-1);
                }
            }
        }

        Weight betterWay = null;
        for (Weight weight : winArray) {
            if (betterWay == null) {
                betterWay = weight;
            }
            if (weight.getCount() > betterWay.getCount()) betterWay = weight;
        }

        String[] splitado = betterWay.getMethod().split(" - ");
        for (String sp : splitado) {
            if (TextUtils.isEmpty(boardGame.get(sp))) {
                position.put("x", Integer.parseInt(sp.split("-")[0]));
                position.put("y", Integer.parseInt(sp.split("-")[1]));
                return position;
            }
        }

        return easy();
    }

    private HashMap<String, Integer> hard() {
        HashMap<String, Integer> position = new HashMap<>();
        positionToFinishGame();

        if (!boardGame.containsValue("o")) {
            if (boardGame.get("0-0").equals("x") || boardGame.get("0-2").equals("x") || boardGame.get("2-0").equals("x") || boardGame.get("2-2").equals("x")) {
                position.put("x", 1);
                position.put("y", 1);
                return position;
            }
        }

        for (Weight weight : winArray) {
            for (String cp : humanHistory) {
                if (weight.getMethod().contains(cp)) {
                    weight.setCount(weight.getCount() + 1);
                }
            }
        }

        for (Weight weight : winArray) {
            for (String hp : computerHistory) {
                if (weight.getMethod().contains(hp)) {
                    weight.setCount(weight.getCount() - 1);
                }
            }
        }

        Weight betterWay = null;
        for (Weight weight : winArray) {
            if (betterWay == null) {
                betterWay = weight;
            }
            if (weight.getCount() > betterWay.getCount()) {
                betterWay = weight;
            } else if (weight.getCount() == -2) {
                betterWay = weight;
                break;
            }

        }


        String[] splitado = betterWay.getMethod().split(" - ");
        for (String sp : splitado) {
            if (TextUtils.isEmpty(boardGame.get(sp))) {
                position.put("x", Integer.parseInt(sp.split("-")[0]));
                position.put("y", Integer.parseInt(sp.split("-")[1]));
                return position;
            }
        }

        return easy();

    }


    private void positionToFinishGame() {
        /*
        0-0 - 1-0 - 2-0  X - -
                         X - -
                         X - -


        0-0 - 1-1 - 2-2 X - -
                        - X -
                        - - X

        0-0 - 0-1 - 0-2 X X X
                        - - -
                        - - -

        2-2 - 1-2 - 0-2 - - X
                        - - X
                        - - X

        2-2 - 2-1 - 2-0 - - -
                        - - -
                        X X X

        2-0 - 1-1 - 0-2 - - X
                        - X -
                        X - -

        1-0 - 1-1 - 1-2 - - -
                        X X X
                        - - -

        0-1 - 1-1 - 2-1 - X -
                        - X -
                        - X -
         */

        winArray = null;
        winArray = new ArrayList<>();
        winArray.add(new Weight("0-0 - 1-0 - 2-0"));
        winArray.add(new Weight("0-0 - 1-1 - 2-2"));
        winArray.add(new Weight("0-0 - 0-1 - 0-2"));
        winArray.add(new Weight("2-2 - 1-2 - 0-2"));
        winArray.add(new Weight("2-2 - 2-1 - 2-0"));
        winArray.add(new Weight("2-0 - 1-1 - 0-2"));
        winArray.add(new Weight("1-0 - 1-1 - 1-2"));
        winArray.add(new Weight("0-1 - 1-1 - 2-1"));
    }

    public void clearHistory() {
        computerHistory = new ArrayList<>();
        humanHistory = new ArrayList<>();
    }

}
