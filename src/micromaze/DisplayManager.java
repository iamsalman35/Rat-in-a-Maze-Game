package MicroMaze.src.micromaze;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class DisplayManager extends JPanel {
    int c = 0;
    private int steps;
    private int pos[][] = new int[10000][2];
    private int size;
    private int maze1[][] = new int[10000][10000];
    private static final long serialVersionUID = 1L;
    final int margin = 50;
    final int cellSize = 50;
    private int i1, j1;
    private int nCols;
    private int nRows;
    private int pRows;
    private int X_end, Y_end;
    int offset = (margin + cellSize) / 2 + 18;

    public void sendpathValue(int x, int y, int i) {
        pos[i][0] = x;
        pos[i][1] = y;
        System.out.println(pos[i][0] + ", " + pos[i][1]);
    }

    public void sendpathSize(int x) {
        steps = x;
        System.out.println("No. of Steps - " + x);
    }

    public void getSize(int x) {
        size = x;
        // System.out.println("size - " + size);
    }

    public void creatingDecimalMaze(int x, int y, String s) {
        maze1[x][y] = Integer.parseInt(s, 2);
    }

    public void initialize(int x, int y) {
        nCols = size;
        nRows = size;
        pRows = steps;
        X_end = x;
        Y_end = y;
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g1 = (Graphics2D) gg;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setStroke(new BasicStroke(5));
        g1.setColor(Color.black);
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                int x = margin + c * cellSize;
                int y = margin + r * cellSize;
                switch (maze1[r][c]) {
                    case 0: {
                        g1.drawLine(x, y, x, y);
                        break;
                    }
                    case 1: {
                        g1.drawLine(x, y, x + cellSize, y);
                        break;
                    }
                    case 2: {
                        g1.drawLine(x, y, x, y + cellSize);
                        break;
                    }
                    case 3: {
                        g1.drawLine(x, y, x, y + cellSize);
                        g1.drawLine(x, y, x + cellSize, y);
                        break;
                    }
                    case 4: {
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        break;
                    }
                    case 5: {
                        g1.drawLine(x, y, x + cellSize, y);
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        break;
                    }
                    case 6: {
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        g1.drawLine(x, y, x, y + cellSize);
                        break;
                    }
                    case 7: {
                        g1.drawLine(x, y, x, y + cellSize);
                        g1.drawLine(x, y, x + cellSize, y);
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        break;
                    }
                    case 8: {
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 9: {
                        g1.drawLine(x, y, x + cellSize, y);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 10: {
                        g1.drawLine(x, y, x, y + cellSize);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 11: {
                        g1.drawLine(x, y, x, y + cellSize);
                        g1.drawLine(x, y, x + cellSize, y);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 12: {
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 13: {
                        g1.drawLine(x, y, x + cellSize, y);
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 14: {
                        g1.drawLine(x, y, x, y + cellSize);
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                    case 15: {
                        g1.drawLine(x, y, x + cellSize, y);
                        g1.drawLine(x, y, x, y + cellSize);
                        g1.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                        g1.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                        break;
                    }
                }
            }
        }
        g1.setColor(Color.green);
        int m = offset + Y_end * cellSize;
        int n = offset + X_end * cellSize;
        g1.fillOval(m, n, 15, 15);

        g1.setColor(Color.BLACK);
        g1.drawString("No of Steps : ", 25, 25);
        g1.drawString(String.valueOf(c), 105, 25);

        g1.setColor(Color.RED);
        g1.fillOval(((offset) + j1 * cellSize), ((offset) + i1 * cellSize), 15, 15);
    }

    void sound(int x) {
        if (pos[x - 1][0] == pos[x + 1][0]) {
            if (pos[x - 1][1] == pos[x + 1][1]) {
                File Clap = new File("wall.wav");
                PlaySound(Clap);
            } // else {
              // File Run = new File("run.wav");
              // PlaySound(Run);
              // }
        }
    }

    void PlaySound(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {
        }
    }

    public void drawpath() {
        for (int i = 0; i < pRows; i++) {
            i1 = pos[i][0];
            j1 = pos[i][1];
            if (i != 0)
                sound(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
            c = c + 1;
            repaint();
        }
    }
}