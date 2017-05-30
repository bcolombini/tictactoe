package com.example.bcolombini.tictactoe.util;

import android.text.TextUtils;
import android.widget.Button;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WinnerImp implements WinnerInterface {


    @Override
    public boolean checked(HashMap<String, Button> board) {
        String[][] boardArray = getBoard(board);

        if (boardArray[0][0].equals(boardArray[1][1]) && boardArray[1][1].equals(boardArray[2][2]) && !TextUtils.isEmpty(boardArray[2][2])) {
            return true;
        }

        if (boardArray[2][0].equals(boardArray[1][1]) && boardArray[1][1].equals(boardArray[0][2]) && !TextUtils.isEmpty(boardArray[2][0])) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            if (boardArray[i][0].equals(boardArray[i][1]) && boardArray[i][1].equals(boardArray[i][2]) && !TextUtils.isEmpty(boardArray[i][0])) {
                return true;
            } else if (boardArray[0][i].equals(boardArray[1][i]) && boardArray[1][i].equals(boardArray[2][i]) && !TextUtils.isEmpty(boardArray[0][i])) {
                return true;
            }

        }
        return false;
    }


    private String[][] getBoard(HashMap<String, Button> board) {
        String[][] boardArray = new String[3][3];
        Iterator it = board.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Button bt = (Button) pair.getValue();
            String[] keyName = pair.getKey().toString().split("-");
            int x = Integer.parseInt(keyName[0]);
            int y = Integer.parseInt(keyName[1]);
            boardArray[x][y] = bt.getText().toString();
        }

        return boardArray;
    }
}
