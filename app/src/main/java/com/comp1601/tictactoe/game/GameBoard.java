package com.comp1601.tictactoe.game;

public class GameBoard {

    public char[] board = new char[9];
    public static char player = 'x', opponent = 'o';

    public GameBoard() {
        for (int i = 0; i<9; i++){
            board[i] = '-';
        }

        for (int i = 0; i<9; i++){
            System.out.println(board[i]);
        }

    }


    public char[] getBoard() {
        return board;
    }

    public static Boolean isMovesLeft(char board[])
    {
        for (int i = 0; i < 9; i++)
            if (board[i] == '-')
                return true;
        return false;
    }

    public  void setBoard(int x, char symbol){
        board[x] = symbol;
    }

    public int[] checkWin(char[] b){
        int[] temp = new int[3];
        temp[0] = -1;
        temp[1] = -1;
        temp[2] = -1;
        if (b[0] == b[1] && b[1] == b[2]) {
            temp[0] = 0;
            temp[1] = 1;
            temp[2] = 2;
        }
        else if (b[3] == b[4] && b[4] == b[5]){
            temp[0] = 3;
            temp[1] = 4;
            temp[2] = 5;
        }
        else if (b[6] == b[7] && b[7] == b[8]){
            temp[0] = 6;
            temp[1] = 7;
            temp[2] = 8;
        }
        else if (b[0] == b[3] && b[3] == b[6]) {
            temp[0] = 0;
            temp[1] = 3;
            temp[2] = 6;
        }
        else if (b[1] == b[4] && b[4] == b[7]){
            temp[0] = 1;
            temp[1] = 4;
            temp[2] = 7;
        }
        else if (b[2] == b[5] && b[5] == b[8]){
            temp[0] = 2;
            temp[1] = 5;
            temp[2] = 8;
        }
        else if (b[0] == b[4] && b[4] == b[8]){
            temp[0] = 0;
            temp[1] = 4;
            temp[2] = 8;
        }
        else if (b[2] == b[4] && b[4] == b[6]){
            temp[0] = 2;
            temp[1] = 4;
            temp[2] = 6;
        } else {
            return temp;
        };
        return temp;
    }
}
