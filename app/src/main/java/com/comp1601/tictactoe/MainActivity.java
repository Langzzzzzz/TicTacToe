package com.comp1601.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.comp1601.tictactoe.game.BotPlayer;
import com.comp1601.tictactoe.game.GameBoard;
import com.comp1601.tictactoe.game.HumanPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Button leftUpButton;
    public Button upMidButton;
    public Button rightUpButton;
    public Button leftMidButton;
    public Button midButton;
    public Button rightMidButton;
    public Button leftLowButton;
    public Button lowMidButton;
    public Button rightLowButton;
    public Button resetButton;
    public Button restartButton;
    public TextView humanTextView;
    public TextView computerTextView;
    public Button[]ButtonList = new Button[9];
    public char[]board = new char[9];

    public int[] flag = new int[3];
    public Boolean check = false;
    public int result;
    public int humanCount = 0;
    public int computerCount = 0;
    public  int turn = 1;
    public boolean tie =false;
    public int roundCount = 1;

    HumanPlayer hp = new HumanPlayer('x');
    BotPlayer bp = new BotPlayer('o');
    GameBoard gb = new GameBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        leftUpButton = findViewById(R.id.leftUpButton);
        upMidButton = findViewById(R.id.upMidButton);
        rightUpButton = findViewById(R.id.rightUpButton);
        leftMidButton = findViewById(R.id.leftMidButton);
        midButton = findViewById(R.id.midButton);
        rightMidButton = findViewById(R.id.rightMidButton);
        leftLowButton = findViewById(R.id.leftLowButton);
        lowMidButton = findViewById(R.id.lowMidButton);
        rightLowButton = findViewById(R.id.rightLowButton);
        resetButton = findViewById(R.id.button_reset);
        restartButton= findViewById(R.id.button_next);

        ButtonList[0] =leftUpButton;
        ButtonList[1] =upMidButton;
        ButtonList[2] =rightUpButton;
        ButtonList[3] =leftMidButton;
        ButtonList[4] =midButton;
        ButtonList[5] =rightMidButton;
        ButtonList[6] =leftLowButton;
        ButtonList[7] = lowMidButton;
        ButtonList[8] =rightLowButton;

        humanTextView = (TextView) findViewById(R.id.text_view_p1);
        computerTextView = (TextView) findViewById(R.id.text_view_p2);

        for (int i = 0; i<9; i++){
            ButtonList[i].setOnClickListener(this::onClick);
        }

        if(savedInstanceState != null){
            gb.getBoard()[0] = savedInstanceState.getChar("0", '-');
            gb.getBoard()[1] = savedInstanceState.getChar("1", '-');
            gb.getBoard()[2] = savedInstanceState.getChar("2", '-');
            gb.getBoard()[3] = savedInstanceState.getChar("3", '-');
            gb.getBoard()[4] = savedInstanceState.getChar("4", '-');
            gb.getBoard()[5] = savedInstanceState.getChar("5", '-');
            gb.getBoard()[6] = savedInstanceState.getChar("6", '-');
            gb.getBoard()[7] = savedInstanceState.getChar("7", '-');
            gb.getBoard()[8] = savedInstanceState.getChar("8", '-');

            flag[0] = savedInstanceState.getInt("flag[0]", 0);
            flag[1] = savedInstanceState.getInt("flag[1]", 0);
            flag[2] = savedInstanceState.getInt("flag[2]", 0);
            check = savedInstanceState.getBoolean("Check", false);
            result = savedInstanceState.getInt("result", 0);
            turn = savedInstanceState.getInt("turn", 1);
            humanCount = savedInstanceState.getInt("humanCount", 0);
            computerCount = savedInstanceState.getInt("computerCount", 0);
            tie  = savedInstanceState.getBoolean("tie", false);
            roundCount = savedInstanceState.getInt("roundcount", 1);

            for (int i =0; i<9; i++){
                if(gb.getBoard()[i]!='-'){
                    ButtonList[i].setText(String.valueOf(gb.getBoard()[i]));
                }
            }

            humanTextView.setText("X: " + humanCount);
            computerTextView.setText("O: " + computerCount);
            if(result>0){
                for(int i = 0; i< flag.length;i++){
                    ButtonList[flag[i]].setBackgroundColor(Color.RED);
                }
            }
        }


        resetButton.setOnClickListener(v -> {
            gb = new GameBoard();
            changturn();
            for (int i=0; i<9;i++){
                ButtonList[i].setText(" ");
                ButtonList[i].setBackgroundColor(Color.rgb(57,131,119));
            }
            for (int i=0; i<flag.length;i++){
                flag[i] = 0;
            }
            check = false;
            result = 0;
            turn = 1;
            computerCount = 0;
            humanCount = 0;
            roundCount=0;
            computerTextView.setText("O: " + computerCount);
            humanTextView.setText("X: " + humanCount);
            setEnable();
        });

        restartButton.setOnClickListener(v -> {
            String s =printBoard(gb.getBoard());
            Log.i("Game Board: \n", s);
            changturn();
            gb = new GameBoard();
            for (int i=0; i<9;i++){
                ButtonList[i].setText(" ");
                ButtonList[i].setBackgroundColor(Color.rgb(57,131,119));
            }
            for (int i=0; i<flag.length;i++){
                flag[i] = 0;
            }
            check = false;
            result = 0;
            turn = 1;
            setEnable();
            if (roundCount%2 == 0){
                botTurn();
            }else{
                return;
            }
        });

    }

    public void changturn(){
        if (hp.getSymbol()== 'x'){
            hp = new HumanPlayer('o');
            bp = new BotPlayer('x');
        }else{
            hp = new HumanPlayer('x');
            bp = new BotPlayer('o');
        }
    }

    public int botTurn(){
        int position  = bp.move(gb,bp.getSymbol());
        gb.getBoard()[position] = bp.getSymbol();
        ButtonList[position].setText(String.valueOf(bp.getSymbol()));
        flag = gb.checkWin(gb.getBoard());
        String s =printBoard(gb.getBoard());
        Log.i("Game Board: \n", s);
        for (int i = 0; i<flag.length; i++){
            result += flag[i];
        }
        if(result>0){
            setButtonColor(flag);
            setDisable();
            turn =3;
            roundCount++;
            return turn;
        }
        result =0;
        turn = 1;
        return turn;
    }

    public void setDisable(){
        for (int i=0; i<ButtonList.length;i++){
            ButtonList[i].setEnabled(false);
        }
    }

    public void setEnable(){
        for (int i=0; i<ButtonList.length;i++){
            ButtonList[i].setEnabled(true);
        }
    }

    public String printBoard(char[] board){
        String s ="";
        for (int i=0; i<3; i++){
            s+=String.valueOf(board[i]);
            s+=" | ";
        }
        s+='\n';
        for (int i=3; i<6; i++){
            s+=String.valueOf(board[i]);
            s+=" | ";
        }
        s+='\n';
        for (int i=6; i<9; i++){
            s+=String.valueOf(board[i]);
            s+=" | ";
        }
        s+='\n';
        return s;
    }

    public void setButtonColor(int[] lis){
        for (int i = 0; i<lis.length; i++){
            ButtonList[lis[i]].setBackgroundColor(Color.RED);
        }
        if (gb.getBoard()[lis[0]] == 'x'){
            humanCount++;
            humanTextView.setText("X: "+ humanCount);
        }else if (gb.getBoard()[lis[0]] == 'o'){
            computerCount++;
            computerTextView.setText("o: "+ computerCount);
        }
    }

    public int humanMove(int position){
        if(gb.getBoard()[position] == 'x' || gb.getBoard()[position] == 'o'){
            Toast.makeText(getApplicationContext(),"This button already set...", Toast.LENGTH_SHORT).show();
            turn = 1;
            return turn;
        }
        gb.getBoard()[position] = hp.getSymbol();
        ButtonList[position].setText(String.valueOf(hp.getSymbol()));
        flag = gb.checkWin(gb.getBoard());
        String s =printBoard(gb.getBoard());
        Log.i("Game Board: \n", s);
        for (int i = 0; i<flag.length; i++){
            result += flag[i];
            Log.i("Result: ", String.valueOf(result));
        }
        if(result>0){
            setButtonColor(flag);
            setDisable();
            turn =3;
            roundCount++;
            return turn;
        }
        result =0;
        turn =2;
        return turn;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftUpButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.upMidButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(1);
                        Log.i("BUtton Info: ", "1 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rightUpButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(2);
                        Log.i("BUtton Info: ", "2 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.leftMidButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(3);
                        Log.i("BUtton Info: ", "3 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.midButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(4);
                        Log.i("BUtton Info: ", "4 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rightMidButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(5);
                        Log.i("BUtton Info: ", "5 clicked");
                    };
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.leftLowButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(6);
                        Log.i("BUtton Info: ", "6 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lowMidButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(7);
                        Log.i("BUtton Info: ", "7 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rightLowButton:
                try {
                    //todo...
                    while (turn == 1){
                        humanMove(8);
                        Log.i("BUtton Info: ", "8 clicked");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        tie = gb.checkTie(gb.getBoard());
        if (tie){
            Toast.makeText(getApplicationContext(),"Tie.", Toast.LENGTH_SHORT).show();
            setDisable();
            roundCount++;
            return;
        }

        while(turn == 2){
            botTurn();
        }
        tie = gb.checkTie(gb.getBoard());
        if (tie){
            Toast.makeText(getApplicationContext(),"Tie.", Toast.LENGTH_SHORT).show();
            setDisable();
            roundCount++;
            return;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putChar("0", gb.getBoard()[0]);
        savedInstanceState.putChar("1", gb.getBoard()[1]);
        savedInstanceState.putChar("2", gb.getBoard()[2]);
        savedInstanceState.putChar("3", gb.getBoard()[3]);
        savedInstanceState.putChar("4", gb.getBoard()[4]);
        savedInstanceState.putChar("5", gb.getBoard()[5]);
        savedInstanceState.putChar("6", gb.getBoard()[6]);
        savedInstanceState.putChar("7", gb.getBoard()[7]);
        savedInstanceState.putChar("8", gb.getBoard()[8]);
        savedInstanceState.putBoolean("Check", check);
        savedInstanceState.putBoolean("tie", tie);
        savedInstanceState.putInt("roundcount", roundCount);
        savedInstanceState.putInt("result", result);
        savedInstanceState.putInt("turn", turn);
        savedInstanceState.putInt("humanCount", humanCount);
        savedInstanceState.putInt("computerCount", computerCount);
        savedInstanceState.putInt("flag[0]", flag[0]);
        savedInstanceState.putInt("flag[1]", flag[1]);
        savedInstanceState.putInt("flag[2]", flag[2]);
        Log.i("Change ori", "onSaveInstanceState(Bundle)");
    }
}
