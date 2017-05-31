package com.example.bcolombini.tictactoe.util.level;

import android.widget.Button;

import java.util.HashMap;
import java.util.Random;

public class LevelEasy extends Level {

    public LevelEasy(HashMap<String, Button> boardGame) {
        super(boardGame);
    }

    @Override
    public HashMap<String, Integer> getMove() {
        return randomPosition();
    }

}
