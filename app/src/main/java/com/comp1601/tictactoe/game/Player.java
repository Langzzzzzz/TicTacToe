package com.comp1601.tictactoe.game;

public abstract class Player {

    protected String name;
    protected char symbol;


    public abstract char getSymbol();
    public abstract void moveForTest(char[] b, int num, char symbol);


    public String getName() {
        return name;
    }
}
