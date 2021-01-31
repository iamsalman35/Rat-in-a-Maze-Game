/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MicroMaze.src.micromaze;

/**
 *
 * @author RICHIK NANDY
 */
public class Maze {
    private String s;
    private int state;

    public void storesurroundCell(String a) {
        s = a;
    }

    public String getsurroundCell() {
        return s;
    }

    public void changeState(int a) {
        state = a;
    }

    public int getState() {
        return state;
    }
}
