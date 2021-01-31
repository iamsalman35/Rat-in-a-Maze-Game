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

import java.util.*;
import java.io.*;

public class MazeMaker {
    private String m[][] = new String[100][100];
    private int size = 0;
    private int X_start;
    private int Y_start;
    private int X_end;
    private int Y_end;

    public void MakeMaze() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the File: (Give the Extension also!)");
            String fileName = sc.nextLine();
            File file = new File(fileName);
            int ar[][] = new int[1000][1000];
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            String s;
            while ((st = br.readLine()) != null) {
                s = st;
                MazeCreator(ar, s, size);
                size += 1;
            }
            createMaze(ar);
        } catch (FileNotFoundException e) {
            System.out.println("Error finding input file " + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error! " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void MazeCreator(int ar[][], String s, int size) {
        int j = 0;
        char ch;
        int k = 0;
        for (j = 0; j < s.length(); j += 2) {
            ch = s.charAt(j);
            if (ch == '+')
                ar[size][k++] = 1;
            else if (ch == '-')
                ar[size][k++] = 1;
            else if (ch == ' ')
                ar[size][k++] = 0;
            else if (ch == '|')
                ar[size][k++] = 1;
            else if (ch == 'S') {
                ar[size][k++] = 0;
                X_start = (size - 1) / 2;
                Y_start = (k - 2) / 2;
            } else if (ch == 'E') {
                ar[size][k++] = 0;
                X_end = (size - 1) / 2;
                Y_end = (k - 2) / 2;
            }
        }
    }

    public void createMaze(int ar[][]) {

        System.out.println("The Raw Matrix");
        for (int i = 0; i < size - 2; i++) {
            for (int j = 0; j < size - 2; j++)
                System.out.print(ar[i][j] + " ");
            System.out.println();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                m[i][j] = checkNeighbourhood(i * 2 + 1, j * 2 + 1, ar);
        }
    }

    public String checkNeighbourhood(int p, int q, int ar[][]) {
        String s1 = "";
        s1 = s1 + String.valueOf(ar[p][q + 1]);
        s1 = s1 + String.valueOf(ar[p + 1][q]);
        s1 = s1 + String.valueOf(ar[p][q - 1]);
        s1 = s1 + String.valueOf(ar[p - 1][q]);
        return s1;
    }

    public String getValue(int i, int j) {
        return m[i][j];
    }

    public int getSize() {
        size = (size - 3) / 2;
        return size;
    }

    public int getX_start() {
        return X_start;
    }

    public int getY_start() {
        return Y_start;
    }

    public int getX_end() {
        return X_end;
    }

    public int getY_end() {
        return Y_end;
    }
}
