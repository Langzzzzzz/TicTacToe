package com.comp1601.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

            if(result>0){
                for (int i =0; i<9; i++){
                    if(gb.getBoard()[i]!='-'){
                        ButtonList[i].setText(String.valueOf(gb.getBoard()[i]));
                    }
                }
            }

            computerTextView.setText("Computer: " + computerCount);
            humanTextView.setText("Human: " + humanCount);
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
            computerTextView.setText("Computer: " + computerCount);
            humanTextView.setText("Human: " + humanCount);
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
            turn = 2;
            setEnable();
            if (bp.getSymbol() == 'x' && gb.isMovesLeft(gb.getBoard())){
                s = printBoard(gb.getBoard());
                Log.i("Game Board: \n", s);
                botTurn();
                board = gb.getBoard();
                flag = gb.checkWin(board,'o');

                board = gb.getBoard();
                //gb.print(board);
                check = gb.isMovesLeft(board);
                for(int i = 0; i< flag.length;i++){
                    result += flag[i];
                }
                if(result > 0){
                    System.out.println("o win");
                    for(int i = 0; i< flag.length;i++){
                        ButtonList[flag[i]].setBackgroundColor(Color.RED);
                    }
                    setDisable();
                    computerCount++;
                    computerTextView.setText("Computer: " + computerCount);
                    return;
                }
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

    public void botTurn(){
        String s =printBoard(gb.getBoard());
        Log.i("Game Board: \n", s);
        board = gb.getBoard();
        int position  = bp.move(gb,bp.getSymbol());
        System.out.println(position);
        if (position == -1 ){
            Toast.makeText(getApplicationContext(),"Tie", Toast.LENGTH_SHORT).show();
        }else{
            ButtonList[position].setText(String.valueOf(bp.getSymbol()));
            gb.setBoard(position,bp.getSymbol());}

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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftUpButton:
                try {
                    gb.setBoard(0,hp.getSymbol());
                    leftUpButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.upMidButton:
                try {
                    gb.setBoard(1,hp.getSymbol());
                    upMidButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rightUpButton:
                try {
                    gb.setBoard(2,hp.getSymbol());
                    rightUpButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.leftMidButton:
                try {
                    //human turn
                    gb.setBoard(3,hp.getSymbol());
                    leftMidButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.midButton:
                try {
                    //human turn
                    gb.setBoard(4,hp.getSymbol());
                    midButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rightMidButton:
                try {
                    //human turn
                    gb.setBoard(5,hp.getSymbol());
                    rightMidButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.leftLowButton:
                try {
                    //human turn
                    gb.setBoard(6,hp.getSymbol());
                    leftLowButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.lowMidButton:
                try {
                    //human turn
                    gb.setBoard(7,hp.getSymbol());
                    lowMidButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rightLowButton:
                try {
                    //human turn
                    gb.setBoard(8,hp.getSymbol());
                    rightLowButton.setText(String.valueOf(hp.getSymbol()));
                    board = gb.getBoard();
                    flag = gb.checkWin(board,hp.getSymbol());
                    turn =2;
                    String s =printBoard(gb.getBoard());
                    Log.i("Game Board: \n", s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }


        board = gb.getBoard();
        //gb.print(board);
        check = gb.isMovesLeft(board);
        flag = gb.checkWin(board,hp.getSymbol());
        for(int i = 0; i< flag.length;i++){
            result += flag[i];
        }
        if(result > 0){
            System.out.println("x win");
            String s =printBoard(gb.getBoard());
            Log.i("Game Board: \n", s);
            for(int i = 0; i< flag.length;i++){
                ButtonList[flag[i]].setBackgroundColor(Color.RED);
            }
            humanCount++;
            setDisable();
            humanTextView.setText("Human: " + humanCount);
            return;
        }
        if(turn == 2 && gb.isMovesLeft(gb.getBoard())){
            botTurn();
            String s =printBoard(gb.getBoard());
            Log.i("Game Board: \n", s);
            board = gb.getBoard();
            flag = gb.checkWin(board,bp.getSymbol());

            board = gb.getBoard();
            //gb.print(board);
            check = gb.isMovesLeft(board);
            for(int i = 0; i< flag.length;i++){
                result += flag[i];
            }
            if(result > 0){
                System.out.println("o win");
                for(int i = 0; i< flag.length;i++){
                    ButtonList[flag[i]].setBackgroundColor(Color.RED);
                }
                setDisable();
                computerCount++;
                computerTextView.setText("Computer: " + computerCount);
                return;
            }
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
