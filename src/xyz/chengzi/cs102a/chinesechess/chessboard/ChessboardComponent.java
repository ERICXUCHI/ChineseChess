package xyz.chengzi.cs102a.chinesechess.chessboard;

import com.sun.org.apache.regexp.internal.RE;
import xyz.chengzi.cs102a.chinesechess.chess.*;
import xyz.chengzi.cs102a.chinesechess.listener.ChessListener;
import xyz.chengzi.cs102a.chinesechess.listener.ChessboardChessListener;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ChessboardComponent extends JComponent {
    private ChessListener chessListener = new ChessboardChessListener(this);
    private ChessComponent[][] chessboard = new ChessComponent[10][9];
    public ChessboardComponent chessboardComponent;
    private ChessColor currentColor = ChessColor.RED;
    public ArrayList<String> moveseq = new ArrayList<>();
    public int count=0;
    JLabel j1 = new JLabel("黑方移动");
    JLabel j2 = new JLabel("红方移动");
    private File music = new File("E:\\南科大\\JAVA\\象棋\\象棋背景音乐.wav");
    private URI uri = music.toURI();
    URL url;

    {
        try {
            url = uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private AudioClip aau = Applet.newAudioClip(url);

    public void playMusic(){
        aau.loop();
    }
    public void stopMusic(){
        aau.stop();
    }


    public ChessboardComponent(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);

        ChessComponent.registerListener(chessListener);
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
            }
        }

        // FIXME: Initialize chessboard for testing only.
//        ChariotInitTestBoard(0, 0, ChessColor.BLACK);
//        ChariotInitTestBoard(0, 8, ChessColor.BLACK);
//        HorseInitTestBoard(0, 1, ChessColor.BLACK);
//        HorseInitTestBoard(0, 7, ChessColor.BLACK);
//        ElephantInitTestBoard(0, 2, ChessColor.BLACK);
//        ElephantInitTestBoard(0, 6, ChessColor.BLACK);
//        AdvisorInitTestBoard(0, 3, ChessColor.BLACK);
//        AdvisorInitTestBoard(0, 5, ChessColor.BLACK);
//        GeneralInitTestBoard(0, 4, ChessColor.BLACK);
//        CannonInitTestBoard(2, 1, ChessColor.BLACK);
//        CannonInitTestBoard(2, 7, ChessColor.BLACK);
//        SoldierInitTestBoard(3, 0, ChessColor.BLACK);
//        SoldierInitTestBoard(3, 2, ChessColor.BLACK);
//        SoldierInitTestBoard(3, 4, ChessColor.BLACK);
//        SoldierInitTestBoard(3, 6, ChessColor.BLACK);
//        SoldierInitTestBoard(3, 8, ChessColor.BLACK);
//
//        ChariotInitTestBoard(9, 0, ChessColor.RED);
//        ChariotInitTestBoard(9, 8, ChessColor.RED);
//        HorseInitTestBoard(9, 1, ChessColor.RED);
//        HorseInitTestBoard(9, 7, ChessColor.RED);
//        ElephantInitTestBoard(9, 2, ChessColor.RED);
//        ElephantInitTestBoard(9, 6, ChessColor.RED);
//        AdvisorInitTestBoard(9, 3, ChessColor.RED);
//        AdvisorInitTestBoard(9, 5, ChessColor.RED);
//        GeneralInitTestBoard(9, 4, ChessColor.RED);
//        CannonInitTestBoard(7, 1, ChessColor.RED);
//        CannonInitTestBoard(7, 7, ChessColor.RED);
//        SoldierInitTestBoard(6, 0, ChessColor.RED);
//        SoldierInitTestBoard(6, 2, ChessColor.RED);
//        SoldierInitTestBoard(6, 4, ChessColor.RED);
//        SoldierInitTestBoard(6, 6, ChessColor.RED);
//        SoldierInitTestBoard(6, 8, ChessColor.RED);
    }

    public String toString(){
        String[] str;
        str= getChess();
        String s="";
        if (currentColor.getColor() == Color.BLACK){
            s="@LAST_MOVER=RED\n"+"@@\n"+"\n";
            for (int i=0;i<11;i++){
                s+= str[i]+"\n";
            }
        }
        else if (currentColor.getColor() == Color.RED){
            s="@LAST_MOVER=BLACK\n"+"@@\n"+"\n";
            for (int i=0;i<11;i++){
                s+= str[i] +"\n";
            }
        }
        return s;
    }

    public String getMoveseq(String s){
        for (String str : moveseq){
            s += str;
        }
        return s;
    }

    public void getNewChessBoard(String[] content) {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
            }
        }
        String Line = null;
        int x = 0;
        int r = 0;
        int q = 0;
        int p = content.length;
        int t = content.length;
        int C,c,H,h,E,e,A,a,G,g,N,n,S,s;
        C = c = H = h = E = e = A = a = G = g = N = n = S = s = 0;
        for (int i = 0; i < content.length; i++) {
            if (content[i].equals("---------")){
                r++;
            }
            if (content[i].equals("")){
                q++;
            }
        }
        while (true){
            if (r != 1){
                for (int i = 0; i < chessboard.length; i++) {
                    for (int j = 0; j < chessboard[i].length; j++) {
                        putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
                    }
                }
                JOptionPane.showMessageDialog(null,"demo1.chessboard " + ": " +  "River Missing","ERROR",JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if (q != 1){
                for (int i = 0; i < chessboard.length; i++) {
                    for (int j = 0; j < chessboard[i].length; j++) {
                        putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
                    }
                }
                JOptionPane.showMessageDialog(null,"demo1.chessboard " + ": " +  "Space Missing","ERROR",JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if (x == content.length){
                break;
            }
            Line = content[x];
            if (Line == null) {
                break;
            } else if (Line.equals("@@")) {
                p = x + 2;
                x++;
            } else if (Line.startsWith("@LAST_MOVER=BLACK")) {
                currentColor = ChessColor.RED;
                x++;
            } else if (Line.startsWith("@LAST_MOVER=RED")) {
                currentColor = ChessColor.BLACK;
                x++;
            } else if (x >= p && x <= p + 10) {
                if (content[x].equals("---------")) {
                    t = x;
                    x++;
                } else {
                    for (int i = 0; i < Line.length(); i++) {
                        if (Line.length() != 9) {
                            JOptionPane.showMessageDialog(null, "demo1.chessboard " + (x + 1 - p) + ": " + "Invalid Dimension", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        } else {
                            switch (Line.charAt(i)) {
                                case 'C':
                                    C++;
                                    if (x < t) {
                                        ChariotInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        ChariotInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'H':
                                    H++;
                                    if (x < t) {
                                        HorseInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        HorseInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'E':
                                    E++;
                                    if (x < t) {
                                        ElephantInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        ElephantInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'A':
                                    A++;
                                    if (x < t) {
                                        AdvisorInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        AdvisorInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'G':
                                    G++;
                                    if (x < t) {
                                        GeneralInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        GeneralInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'N':
                                    N++;
                                    if (x < t) {
                                        CannonInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        CannonInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'S':
                                    S++;
                                    if (x < t) {
                                        SoldierInitTestBoard(x - p, i, ChessColor.BLACK);
                                    } else {
                                        SoldierInitTestBoard(x - p - 1, i, ChessColor.BLACK);
                                    }
                                    break;
                                case 'c':
                                    c++;
                                    if (x < t) {
                                        ChariotInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        ChariotInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                                case 'h':
                                    h++;
                                    if (x < t) {
                                        HorseInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        HorseInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                                case 'e':
                                    e++;
                                    if (x < t) {
                                        ElephantInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        ElephantInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                                case 'a':
                                    a++;
                                    if (x < t) {
                                        AdvisorInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        AdvisorInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                                case 'g':
                                    g++;
                                    if (x < t) {
                                        GeneralInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        GeneralInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                                case 'n':
                                    n++;
                                    if (x < t) {
                                        CannonInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        CannonInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                                case 's':
                                    s++;
                                    if (x < t) {
                                        SoldierInitTestBoard(x - p, i, ChessColor.RED);
                                    } else {
                                        SoldierInitTestBoard(x - p - 1, i, ChessColor.RED);
                                    }
                                    break;
                            }
                        }
                    }
                    x++;
                }
            } else {
                x++;
            }


            this.repaint();
        }
        if (C>2||c>2||H>2||h>2||E>2||e>2||A>2||a>2||G!=1||g!=1||N>2||n>2||S>5||s>5){
            for (int i = 0; i < chessboard.length; i++) {
                for (int j = 0; j < chessboard[i].length; j++) {
                    putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
                }
            }
            JOptionPane.showMessageDialog(null,"demo1.chessboard " + ": " +  "Invalid Chess Amount","ERROR",JOptionPane.INFORMATION_MESSAGE);

        }

        if (content.length - p != 11){
            for (int i = 0; i < chessboard.length; i++) {
                for (int j = 0; j < chessboard[i].length; j++) {
                    putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j)));
                }
            }
            JOptionPane.showMessageDialog(null,"demo1.chessboard " + ":" +  "Invalid Dimension","ERROR",JOptionPane.INFORMATION_MESSAGE);
        }
    }



    public String[] getChess(){
        String[] str=new String[11];
        for (int i=0;i<chessboard.length+1;i++){
            str[i]="";
            for (int j=0;j<chessboard[0].length;j++){
                if (i==5){
                    str[5] += "-";
                }
                else if (i<5){
                    if (chessboard[i][j] instanceof EmptySlotComponent){
                        str[i] += ".";
                    }
                    else if (chessboard[i][j] instanceof ChariotChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "c";
                        }
                        else str[i] += "C";
                    }
                    else if (chessboard[i][j] instanceof SoldierChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "s";
                        }
                        else str[i] += "S";
                    }
                    else if (chessboard[i][j] instanceof CannonChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "n";
                        }
                        else str[i] += "N";
                    }
                    else if (chessboard[i][j] instanceof AdvisorChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "a";
                        }
                        else str[i] += "A";
                    }
                    else if (chessboard[i][j] instanceof GeneralChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "g";
                        }
                        else str[i] += "G";
                    }
                    else if (chessboard[i][j] instanceof HorseChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "h";
                        }
                        else str[i] += "H";
                    }
                    else if (chessboard[i][j] instanceof ElephantChessComponent)   {
                        if (chessboard[i][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "e";
                        }
                        else str[i] += "E";
                    }
                }
                else {
                    if (chessboard[i-1][j] instanceof EmptySlotComponent){
                        str[i] += ".";
                    }
                    else if (chessboard[i-1][j] instanceof ChariotChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "c";
                        }
                        else str[i] += "C";
                    }
                    else if (chessboard[i-1][j] instanceof SoldierChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "s";
                        }
                        else str[i] += "S";
                    }
                    else if (chessboard[i-1][j] instanceof CannonChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "n";
                        }
                        else str[i] += "N";
                    }
                    else if (chessboard[i-1][j] instanceof AdvisorChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "a";
                        }
                        else str[i] += "A";
                    }
                    else if (chessboard[i-1][j] instanceof GeneralChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "g";
                        }
                        else str[i] += "G";
                    }
                    else if (chessboard[i-1][j] instanceof HorseChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "h";
                        }
                        else str[i] += "H";
                    }
                    else if (chessboard[i-1][j] instanceof ElephantChessComponent)   {
                        if (chessboard[i-1][j].getChessColor().getColor() ==Color.RED){
                            str[i] += "e";
                        }
                        else str[i] += "E";
                    }
                }

            }
        }
        return str;
    }
    public void removeChess(){
        for (int i=0;i<chessboard.length;i++){
            for(int j=0;j<chessboard[0].length;j++){
                if (!(chessboard[i][j] instanceof EmptySlotComponent))
                    remove(chessboard[i][j]);
            }
        }
    }
    public void MoveBySeq(String[] contain){
        String[] tokens;
        String[] tokens1;
        String s;
        int j = 0;
        for (int i=0;i<contain.length;i++){
            tokens = contain[i].split("#");
            s=tokens[0];

            tokens1 = s.split(" ");
            if (j%2==1){
                if (Integer.parseInt(tokens1[3])-1 > 9 || Integer.parseInt(tokens1[3])-1 < 0 || Integer.parseInt(tokens1[2])-1 > 8 || Integer.parseInt(tokens1[2])-1 < 0
                        || Integer.parseInt(tokens1[1])-1 > 9 || Integer.parseInt(tokens1[1])-1 < 0 || Integer.parseInt(tokens1[0])-1 > 8 || Integer.parseInt(tokens1[0])-1 < 0){
                    JOptionPane.showMessageDialog(null,"demo1.chessmoveseq "+ (i+1)+": "+"Position Out Of Range","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
                else if (chessboard[Integer.parseInt(tokens1[1])-1][Integer.parseInt(tokens1[0])-1] instanceof EmptySlotComponent || chessboard[Integer.parseInt(tokens1[1])-1][Integer.parseInt(tokens1[0])-1].getChessColor() == ChessColor.RED){
                    JOptionPane.showMessageDialog(null,"demo1.chessmoveseq "+ (i+1)+": "+"Invalid From Position","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
                else if (chessboard[Integer.parseInt(tokens1[3])-1][Integer.parseInt(tokens1[2])-1].getChessColor() == ChessColor.BLACK){
                    JOptionPane.showMessageDialog(null,"demo1.chessmoveseq "+ (i+1)+": "+"Invalid To Position","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    ChessboardPoint destination = new ChessboardPoint(Integer.parseInt(tokens1[3]) - 1, Integer.parseInt(tokens1[2]) - 1);
                    if (!chessboard[Integer.parseInt(tokens1[1]) - 1][Integer.parseInt(tokens1[0]) - 1].canMoveTo(chessboard, destination)) {
                        JOptionPane.showMessageDialog(null, "demo1.chessmoveseq "+ (i+1)+": "+"Invalid Move Pattern", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        swapChessComponents(chessboard[Integer.parseInt(tokens1[1]) - 1][Integer.parseInt(tokens1[0]) - 1],
                                chessboard[Integer.parseInt(tokens1[3]) - 1][Integer.parseInt(tokens1[2]) - 1]);
                        swapColor();
                        repaint();
                        j++;
                    }
                }
            }
            else {
                if (10-Integer.parseInt(tokens1[3]) > 9 || 10-Integer.parseInt(tokens1[3]) < 0 || 9-Integer.parseInt(tokens1[2]) > 8 || 9-Integer.parseInt(tokens1[2]) < 0
                        || 10-Integer.parseInt(tokens1[1]) > 9 || 10-Integer.parseInt(tokens1[1]) < 0 || 9-Integer.parseInt(tokens1[0]) > 8 || 9-Integer.parseInt(tokens1[0]) < 0){
                    JOptionPane.showMessageDialog(null,"demo1.chessmoveseq "+ (i+1)+": "+"Position Out of Range","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
                else if (chessboard[10-Integer.parseInt(tokens1[1])][9-Integer.parseInt(tokens1[0])] instanceof EmptySlotComponent || chessboard[10-Integer.parseInt(tokens1[1])][9-Integer.parseInt(tokens1[0])].getChessColor() == ChessColor.BLACK){
                    JOptionPane.showMessageDialog(null,"demo1.chessmoveseq "+ (i+1)+": "+"Invalid From Position","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
                else if (chessboard[10-Integer.parseInt(tokens1[3])][9-Integer.parseInt(tokens1[2])].getChessColor() == ChessColor.RED){
                    JOptionPane.showMessageDialog(null,"demo1.chessmoveseq "+ (i+1)+": "+"Invalid To Position","ERROR",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    ChessboardPoint destination = new ChessboardPoint(10 - Integer.parseInt(tokens1[3]), 9 - Integer.parseInt(tokens1[2]));
                    if (!chessboard[10 - Integer.parseInt(tokens1[1])][9 - Integer.parseInt(tokens1[0])].canMoveTo(chessboard, destination)) {
                        JOptionPane.showMessageDialog(null, "demo1.chessmoveseq "+ (i+1)+": "+"Invalid Move Pattern", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        swapChessComponents(chessboard[10 - Integer.parseInt(tokens1[1])][9 - Integer.parseInt(tokens1[0])],
                                chessboard[10 - Integer.parseInt(tokens1[3])][9 - Integer.parseInt(tokens1[2])]);
                        swapColor();
                        repaint();
                        j++;
                    }
                }
            }
        }
    }

    public ChessComponent[][] getChessboard() {
        return chessboard;
    }

    public ChessComponent getChess(int a,int b){
        return chessboard[a][b];
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();
        if (chessboard[row][col] != null) {
            remove(chessboard[row][col]);
        }
        add(chessboard[row][col] = chessComponent);
    }

    public void giveIn(){
        if (currentColor == ChessColor.RED){
            JOptionPane.showMessageDialog(null,"BLACK WINS","GAME OVER",JOptionPane.INFORMATION_MESSAGE);currentColor = ChessColor.NONE;
        }
        else {
            JOptionPane.showMessageDialog(null,"RED WINS","GAME OVER",JOptionPane.INFORMATION_MESSAGE);currentColor = ChessColor.NONE;
        }
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        count++;
        if (!(chess2 instanceof EmptySlotComponent)) {
            if (chess2 instanceof GeneralChessComponent){
                if (chess2.getChessColor() == ChessColor.RED){
                    JOptionPane.showMessageDialog(null,"BLACK WINS","GAME OVER",JOptionPane.INFORMATION_MESSAGE);currentColor = ChessColor.NONE;
                }
                if (chess2.getChessColor() == ChessColor.BLACK){
                    JOptionPane.showMessageDialog(null,"RED WINS","GAME OVER",JOptionPane.INFORMATION_MESSAGE);currentColor = ChessColor.NONE;
                }

            }
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation()));
        }
        chess1.swapLocation(chess2);
        if (chess1.getChessColor().getColor() == Color.BLACK){
            /*System.out.print((chess2.getChessboardPoint().getY()+1) + " " +  (chess2.getChessboardPoint().getX()+1) + " " + (chess1.getChessboardPoint().getY()+1)
                    + " " + (chess1.getChessboardPoint().getX()+1) +"\n");*/
            moveseq.add(chess2.getChessboardPoint().getY()+1 + " " +  (chess2.getChessboardPoint().getX()+1) + " "
                    + (chess1.getChessboardPoint().getY()+1) + " " + (chess1.getChessboardPoint().getX()+1));
        }
        else if (chess1.getChessColor().getColor() == Color.RED){
            /*System.out.print((9-chess2.getChessboardPoint().getY()) + " " + (10-chess2.getChessboardPoint().getX()) + " " + (9-chess1.getChessboardPoint().getY())
            + " " + (10-chess1.getChessboardPoint().getX()) + "\n");*/
            moveseq.add((9-chess2.getChessboardPoint().getY()) + " " + (10-chess2.getChessboardPoint().getX()) + " "
                    + (9-chess1.getChessboardPoint().getY()) + " " + (10-chess1.getChessboardPoint().getX()));
        }
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessboard[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessboard[row2][col2] = chess2;
    }

    public void swapColor() {
        if (currentColor ==ChessColor.BLACK) currentColor = ChessColor.RED;
        else if (currentColor ==ChessColor.RED) currentColor = ChessColor.BLACK;

    }

    private void ChariotInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new ChariotChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void AdvisorInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new AdvisorChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void CannonInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new CannonChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void ElephantInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new ElephantChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void GeneralInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new GeneralChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void HorseInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new HorseChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void SoldierInitTestBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new SoldierChessComponent(new ChessboardPoint(row, col),
                calculatePoint(row, col), color);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentColor == ChessColor.BLACK){
            remove(j2);
            j1.setLocation(420,400);
            j1.setSize(100,20);
            j1.setForeground(Color.BLACK);
            add(j1);
        }
        else if (currentColor == ChessColor.RED){
            remove(j1);
            j2.setLocation(420,400);
            j2.setSize(100,20);
            j2.setForeground(Color.RED);
            add(j2);
        }
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        paintBoardLine(g, 0, 0, 9, 0);
//        paintBoardLine(g, 0, 8, 9, 8);
//        paintHalfBoard(g, 0);
//        paintHalfBoard(g, 5);
//        paintKingSquare(g, 1, 4);
//        paintKingSquare(g, 8, 4);
    }

    private void paintHalfBoard(Graphics g, int startRow) {
        for (int row = startRow; row < startRow + 5; row++) {
            paintBoardLine(g, row, 0, row, 8);
        }
        for (int col = 0; col < 9; col++) {
            paintBoardLine(g, startRow, col, startRow + 4, col);
        }
    }

    private void paintKingSquare(Graphics g, int centerRow, int centerCol) {
        paintBoardLine(g, centerRow - 1, centerCol - 1, centerRow + 1, centerCol + 1);
        paintBoardLine(g, centerRow - 1, centerCol + 1, centerRow + 1, centerCol - 1);
    }

    private void paintBoardLine(Graphics g, int rowFrom, int colFrom, int rowTo, int colTo) {
        int offsetX = ChessComponent.CHESS_SIZE.width / 2, offsetY = ChessComponent.CHESS_SIZE.height / 2;
        Point p1 = calculatePoint(rowFrom, colFrom), p2 = calculatePoint(rowTo, colTo);
        g.drawLine(p1.x + offsetX, p1.y + offsetY, p2.x + offsetX, p2.y + offsetY);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * (getWidth()-100) / 9, row * (getHeight()-100) / 10);
    }
}
