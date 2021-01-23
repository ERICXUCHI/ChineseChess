package xyz.chengzi.cs102a.chinesechess;

import xyz.chengzi.cs102a.chinesechess.chessboard.ChessboardComponent;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class ChessGameFrame extends JFrame implements ActionListener {

    JFileChooser jfc = new JFileChooser();
    JButton bOpen, bSave;
    JButton Saveseq, Loadseq;
    JButton Retract;
    JButton playMusic,stopMusic;
    JButton giveIn;
    public String contents;
    public String[] content;
    public String[] con;
    public String[] con1;
    private ChessboardComponent chessboard;
    String s = "";

    public ChessGameFrame() {
        setTitle("中国象棋");
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        ImageIcon img = new ImageIcon("src/xyz/chengzi/cs102a/chinesechess/棋盘图片.jpg");
        JLabel imgLabel = new JLabel(img);
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        this.setLayout(null);
        imgLabel.setSize(400,400);
        imgLabel.setLocation(-4,-1);
        Container contain = this.getContentPane();
        ((JPanel) contain).setOpaque(false);
        chessboard = new ChessboardComponent(500, 500);
        add(chessboard);
        JLabel statusLabel = new JLabel("You Are The Best");
        statusLabel.setLocation(150, 410);
        statusLabel.setSize(200, 20);
        add(statusLabel);
//        JLabel river = new JLabel("楚河                                               汉界");
//        river.setLocation(110,188);
//        river.setSize(1000,20);
//        add(river);


        bSave = new JButton("保存棋盘");
        bSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    FileOutputStream fos = new FileOutputStream(new File("1_w.chessboard"));
                    OutputStreamWriter osr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                    BufferedWriter bw = new BufferedWriter(osr);

                    bw.write(chessboard.toString());
                    bw.flush();
                    bw.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        bSave.setLocation(420, 160);
        bSave.setSize(125, 50);
        add(bSave);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setVisible(true);

        bOpen = new JButton("加载棋盘");
        bOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                    //chessboard.removeChess();
                    contents = "";
                    FileInputStream fis = new FileInputStream(new File("Task2/demo1.chessboard"));
                    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);

                    while (true) {
                        String str = br.readLine();
                        if (str == null) {
                            break;
                        } else {
                            contents += (str + "%");
                        }
                    }
                    content = contents.split("%");

//                    for (int i = 0; i < 14; i++) {
//                        String str = br.readLine();
//                        content[i] = str;
//                    }
                    br.close();
                    chessboard.getNewChessBoard(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bOpen.setLocation(420, 100);
        bOpen.setSize(125, 50);
        add(bOpen);

        Saveseq = new JButton("保存棋谱");
        Saveseq.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    s = "@TOTAL_STEP=" + chessboard.count + "\n" + "@@\n" + "\n";
                    FileOutputStream fos = new FileOutputStream(new File("1.chessmoveseq"));
                    OutputStreamWriter osr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                    BufferedWriter bw = new BufferedWriter(osr);

                    for (int i = 0; i < chessboard.moveseq.size(); i++) {
                        s += chessboard.moveseq.get(i) + "\n";
                    }
                    bw.write(s);
                    bw.flush();
                    bw.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Saveseq.setLocation(420, 280);
        Saveseq.setSize(125, 50);
        add(Saveseq);

        Loadseq = new JButton("加载棋谱");
        Loadseq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int i = 0;
                try {
                    FileInputStream fis = new FileInputStream(new File("Task3/demo1.chessmoveseq"));
                    InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);

                    while (true) {
                        String str = br.readLine();
//                        System.out.println(str);
                        if (str == null) {
                            break;
                        } else if (str.equals("") || str.equals("@@")) {
                            continue;
                        } else if (str.startsWith("@TOTAL_STEP")) {
                            StringBuilder s = new StringBuilder();
                            char c;
                            for (int j = 0; j < str.length(); j++) {
                                c = str.charAt(j);
                                if (Character.isDigit(c)) {
                                    s.append(c);
                                }
                            }
                            con = new String[Integer.parseInt(s.toString())];
                            for (int k = 0; k < con.length; k++) {
                                con[k] = "";
                            }
                        } else if (Character.isDigit(str.charAt(0))) {
                            con[i] = str;
                            i++;
                        } else {

                        }
                    }
                    br.close();
                    chessboard.MoveBySeq(con);
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException(con[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Loadseq.setLocation(420, 220);
        Loadseq.setSize(125, 50);
        add(Loadseq);

        Retract = new JButton("悔棋");
        Retract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con1 = new String[chessboard.moveseq.size() - 2];
                try {
                    int s = chessboard.moveseq.size();
                    for (int i = 0; i < s - 2; i++) {
                        con1[i] = chessboard.moveseq.get(i);
                    }
                    chessboard.getNewChessBoard(content);
                    chessboard.moveseq.clear();
                    chessboard.MoveBySeq(con1);
                    chessboard.count = con1.length;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        Retract.setLocation(420, 40);
        Retract.setSize(125, 50);
        add(Retract);

        giveIn = new JButton("认输");
        giveIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.giveIn();
            }
        });
        giveIn.setLocation(420,5);
        giveIn.setSize(125,25);
        add(giveIn);


        playMusic = new JButton("▶");
        playMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.playMusic();
            }

        });
        playMusic.setLocation(420,340);
        playMusic.setSize(60,50);
        add(playMusic);

        stopMusic = new JButton("||");
        stopMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessboard.stopMusic();
            }

        });
        stopMusic.setLocation(485,340);
        stopMusic.setSize(60,50);
        add(stopMusic);


    }





    public static void main(String[] args) {
        ChessGameFrame mainFrame = new ChessGameFrame();
        mainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
