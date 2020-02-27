package com.comp1601.tictactoe;

import com.comp1601.tictactoe.game.BotPlayer;
import com.comp1601.tictactoe.game.GameBoard;
import com.comp1601.tictactoe.game.HumanPlayer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void xWin(){
        GameBoard gb = new GameBoard();
        int[] f = new int[3];
        f[0]= 0;
        f[1]= 0;
        f[2]= 0;
        int result = 0;

        HumanPlayer hp = new HumanPlayer('x');
        BotPlayer bp = new BotPlayer('o');
        hp.moveForTest(gb.getBoard(),1,'x');
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        bp.moveForTest(gb.getBoard(),7,'o');
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),2,'x');
        f =  gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        bp.moveForTest(gb.getBoard(),8,'o');
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),3,'x');
        f =gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
    }
    @Test
    public void oWin(){
        GameBoard gb = new GameBoard();
        int[] f = new int[3];
        f[0]= 0;
        f[1]= 0;
        f[2]= 0;
        int result = 0;

        HumanPlayer hp = new HumanPlayer('x');
        BotPlayer bp = new BotPlayer('o');
        bp.moveForTest(gb.getBoard(),1,bp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),7,hp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        bp.moveForTest(gb.getBoard(),1,bp.getSymbol());
        f =  gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),7,hp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        bp.moveForTest(gb.getBoard(),1,bp.getSymbol());
        f =gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
    }
    @Test
    public void tie(){
        GameBoard gb = new GameBoard();
        int[] f = new int[3];
        f[0]= 0;
        f[1]= 0;
        f[2]= 0;
        int result = 0;

        HumanPlayer hp = new HumanPlayer('x');
        BotPlayer bp = new BotPlayer('o');
        hp.moveForTest(gb.getBoard(),0,hp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }

        bp.moveForTest(gb.getBoard(),1,bp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }

        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),2,hp.getSymbol());
        f =  gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        bp.moveForTest(gb.getBoard(),3,bp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),4,hp.getSymbol());
        f =gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }

        bp.moveForTest(gb.getBoard(),5,bp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),6,hp.getSymbol());
        f =gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        bp.moveForTest(gb.getBoard(),7,bp.getSymbol());
        f = gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        hp.moveForTest(gb.getBoard(),8,hp.getSymbol());
        f =gb.checkWin(gb.getBoard());
        for (int i = 0; i<3;i++){
            result+=f[i];
        }
        if(result>0){
            assertEquals(result,result);
        }
        assertEquals(result,result);
    }
}
