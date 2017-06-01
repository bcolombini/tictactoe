package com.example.bcolombini.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bcolombini.tictactoe.util.WinnerImp;
import com.example.bcolombini.tictactoe.util.level.Level;
import com.example.bcolombini.tictactoe.util.level.LevelEasy;
import com.example.bcolombini.tictactoe.util.level.LevelHard;
import com.example.bcolombini.tictactoe.util.level.LevelMedium;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.app.AlertDialog.Builder;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.human_score)
    TextView humanScoreTextView;
    @BindView(R.id.tie_score)
    TextView tieScoreTextView;
    @BindView(R.id.robot_score)
    TextView robotScoreTextView;
    @BindView(R.id.reset_game)
    Button resetGame;
    @BindView(R.id.activity_game)
    LinearLayout activityGame;
    private WinnerImp winnerImp = new WinnerImp();
    private Level levelImp;

    @BindView(R.id.bt00)
    Button bt00;
    @BindView(R.id.bt01)
    Button bt01;
    @BindView(R.id.bt02)
    Button bt02;
    @BindView(R.id.bt10)
    Button bt10;
    @BindView(R.id.bt11)
    Button bt11;
    @BindView(R.id.bt12)
    Button bt12;
    @BindView(R.id.bt20)
    Button bt20;
    @BindView(R.id.bt21)
    Button bt21;
    @BindView(R.id.bt22)
    Button bt22;
    @BindView(R.id.bt_level)
    Button buttonLevel;


    private HashMap<String, Button> board;
    private String flag = "x";
    private int level = 0;
    private int humanScore = 0;
    private int tieScore = 0;
    private int robotScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        this.board = new HashMap<>();
        board.put("0-0", bt00);
        board.put("0-1", bt01);
        board.put("0-2", bt02);
        board.put("1-0", bt10);
        board.put("1-1", bt11);
        board.put("1-2", bt12);
        board.put("2-0", bt20);
        board.put("2-1", bt21);
        board.put("2-2", bt22);

        levelImp = new LevelEasy(board);
    }

    private boolean markTurn(int x, int y) {
        Button bt = board.get(x + "-" + y);
        if (!TextUtils.isEmpty(bt.getText())) {
            return false;
        }
        bt.setTextColor(getResources().getColor(R.color.bola));
        if (flag.equals("x")) {
            bt.setTextColor(getResources().getColor(R.color.xis));
        }
        bt.setText(flag);
        changeTurn();
        return true;
    }

    private void changeTurn() {
        flag = flag.equals("x") ? "o" : "x";
    }

    @OnClick({R.id.bt00, R.id.bt01, R.id.bt02, R.id.bt10, R.id.bt11, R.id.bt12, R.id.bt20, R.id.bt21, R.id.bt22})
    public void onClickBt(View view) {
        switch (view.getId()) {
            case R.id.bt00:
                markTurn(0, 0);
                break;
            case R.id.bt01:
                markTurn(0, 1);
                break;
            case R.id.bt02:
                markTurn(0, 2);
                break;
            case R.id.bt10:
                markTurn(1, 0);
                break;
            case R.id.bt11:
                markTurn(1, 1);
                break;
            case R.id.bt12:
                markTurn(1, 2);
                break;
            case R.id.bt20:
                markTurn(2, 0);
                break;
            case R.id.bt21:
                markTurn(2, 1);
                break;
            case R.id.bt22:
                markTurn(2, 2);
                break;
        }
        if (isFinished()) return;
        if (!flag.equals("x")) {
            levelClick();
        }
    }

    private boolean isFinished() {
        if (winnerImp.checked(board)) {
            showDialog(flag, false);
            return true;
        }
        Iterator it = board.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Button bt = (Button) pair.getValue();
            if (TextUtils.isEmpty(bt.getText())) {
                return false;
            }
        }
        showDialog(flag, true);
        return true;
    }

    private void levelClick() {
        reloadLevel(level);
        HashMap<String, Integer> position = levelImp.getMove();
        int x = position.get("x");
        int y = position.get("y");
        if (!markTurn(x, y)) {
            levelClick();
        }
        if (isFinished()) return;
    }

    private void showDialog(String s, boolean drawFlag) {
        if (!s.equals("o")) {
            robotScore++;
            robotScoreTextView.setText("Computador: " + robotScore);
        } else if (drawFlag) {
            tieScore++;
            tieScoreTextView.setText("Empate: " + tieScore);
        } else if (!drawFlag && s.equals("o")) {
            humanScore++;
            humanScoreTextView.setText("Humano: " + humanScore);
        }
        s = !s.equals("o") ? "Você perdeu" : "Você ganhou";
        s = drawFlag ? "Empate" : s;
        new Builder(this)
                .setTitle("Acabou")
                .setCancelable(false)
                .setMessage(s)
                .setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onClickClearGame();
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    @OnClick(R.id.bt_level)
    public void onClickChoiceLevel() {
        new Builder(this)
                .setTitle("Level")
                .setItems(R.array.level, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        level = which;
                        onClickClearGame();
                        buttonLevel.setText("level : " + getResources().getStringArray(R.array.level)[which]);
                    }
                })
                .create()
                .show();
    }

    @OnClick(R.id.reset_game)
    public void onClickClearGame() {
        for (Button b : board.values()) {
            b.setText("");
        }
        flag = "x";
        winnerImp = new WinnerImp();
        levelImp.clearHistory();
    }

    private void reloadLevel(int which) {
        switch (which) {
            case 0:
                levelImp = new LevelEasy(board);
                break;
            case 1:
                levelImp = new LevelMedium(board);
                break;
            case 2:
                levelImp = new LevelHard(board);
                break;
            default:
        }
    }
}