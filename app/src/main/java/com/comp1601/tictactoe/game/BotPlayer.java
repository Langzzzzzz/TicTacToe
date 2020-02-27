package com.comp1601.tictactoe.game;
public class BotPlayer extends Player {
    int[] cornor = new int[4];
    int[] mar = new int[4];

    public BotPlayer(char x) {
        cornor[0] = 0;
        cornor[1] = 2;
        cornor[2] = 6;
        cornor[3] = 8;
        mar[0] = 1;
        mar[1] = 3;
        mar[2] = 5;
        mar[3] = 7;
        symbol = x;
    }

    @Override
    public void moveForTest(char[] b, int num, char symbol) {
        b[num] = symbol;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    public void makeMove(char[] board, char symbol, int move){
        board[move] = symbol;
    }

    public char[] dupli(GameBoard board){
        char[] dup = new char[9];
        for (int i = 0; i<board.getBoard().length; i++){
            dup[i] = board.getBoard()[i];
        }
        return dup;
    }

    public boolean check(char[] b, char symbol){
        if ((b[0] == symbol && b[1] == symbol && b[2] == symbol) ||
                (b[3] == symbol && b[4] == symbol && b[5] == symbol) ||
                (b[6] == symbol && b[7] == symbol && b[8] == symbol) ||
                (b[0] == symbol && b[3] == symbol && b[6] == symbol) ||
                (b[1] == symbol && b[4] == symbol && b[7] == symbol) ||
                (b[2] == symbol && b[5] == symbol && b[8] == symbol) ||
                (b[0] == symbol && b[4] == symbol && b[8] == symbol) ||
                (b[2] == symbol && b[4] == symbol && b[6] == symbol)){
            return true;
        };
        return false;
    }

    public boolean isSpaceFree(char[] board, int move){
        return board[move] == '-';
    }

    public int randomMove(GameBoard board, int[] moveList){
        for (int i = 0; i<moveList.length; i++){
            if (isSpaceFree(board.getBoard(),moveList[i])){
                return moveList[i];
            }
        }
        return -1;
    }

    public int move(GameBoard board, char symbol){
        for (int i = 0; i<9;i++){
            char[] dup = dupli(board);
            if (isSpaceFree(dup, i)) {
                makeMove(dup,symbol,i);
                if (check(dup, symbol)){
                    return i;
                }
            }
        }

        for (int i = 0; i<9;i++){
            char[] dup = dupli(board);
            if (isSpaceFree(dup, i)) {
                makeMove(dup,'x',i);
                if (check(dup, 'x')){
                    return i;
                }
            }
        }

        int move = randomMove(board, cornor);
        if (move != -1){
            return move;
        }

        if (isSpaceFree(board.getBoard(),4)){
            return  4;
        }

        return randomMove(board,mar);

    }
}
