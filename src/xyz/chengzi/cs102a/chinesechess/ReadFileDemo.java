package xyz.chengzi.cs102a.chinesechess;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadFileDemo {
    public static void main(String[] args){
        try {
            FileInputStream fis = new FileInputStream(new File("E:\\南科大\\JAVA\\象棋\\1_w.chessboard"));
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            while (true) {
                String str = br.readLine();
                if (null == str) {
                    break;
                }
                System.out.println(str);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
