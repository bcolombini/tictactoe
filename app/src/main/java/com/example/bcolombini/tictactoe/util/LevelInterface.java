package com.example.bcolombini.tictactoe.util;

import android.widget.Button;

import java.util.HashMap;

public interface LevelInterface {

    void choiceLevel(int level);

    HashMap<String, Integer> getPosition();

    void setBoard(HashMap<String, Button> stringButtonHashMap);
}
