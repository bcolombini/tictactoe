package com.example.bcolombini.tictactoe.util.configuration;

import android.text.TextUtils;

import com.example.bcolombini.tictactoe.util.general.Weight;

import java.util.ArrayList;
import java.util.HashMap;

public class PositionFinish {

    private ArrayList<Weight> winArray = null;

    private HashMap<String, String> boardGame = new HashMap<>();


    public PositionFinish(HashMap<String, String> boardGame) {
        this.boardGame = boardGame;

        winArray = new ArrayList<>();
        winArray.add(new Weight("0-0 - 1-0 - 2-0")); // Primeira Coluna
        winArray.add(new Weight("0-1 - 1-1 - 2-1")); // Segunda Coluna
        winArray.add(new Weight("0-2 - 1-2 - 2-2")); // Terceira Coluna

        winArray.add(new Weight("0-0 - 0-1 - 0-2")); // Primeira Linha
        winArray.add(new Weight("1-0 - 1-1 - 1-2")); // Segunda Linha
        winArray.add(new Weight("2-2 - 2-1 - 2-0")); // Terceira Linha

        winArray.add(new Weight("0-0 - 1-1 - 2-2")); // Diagonal \
        winArray.add(new Weight("2-0 - 1-1 - 0-2")); // Diagonal /
        removeIfLineComplete(winArray.size() - 1);
    }

    private void removeIfLineComplete(int i) {
        if (i == -1) return;
        int a = 0;
        for (String key : boardGame.keySet()) {
            if (!TextUtils.isEmpty(boardGame.get(key))) {
                if (winArray.get(i).getMethod().contains(key)) a++;
            }
        }
        if (a == 3) {
            winArray.remove(i);
            removeIfLineComplete(winArray.size() - 1);
        } else {
            removeIfLineComplete(i - 1);
        }
    }

    public ArrayList<Weight> getWinArray() {
        return winArray;
    }
}
