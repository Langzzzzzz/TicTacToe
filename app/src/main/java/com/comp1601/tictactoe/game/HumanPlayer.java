package com.comp1601.tictactoe.game;


public class HumanPlayer extends Player {

    public HumanPlayer(char x) {
        symbol = x;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public void moveForTest(char[] b, int num, char symbol) {
        b[num] = symbol;
    }

}
